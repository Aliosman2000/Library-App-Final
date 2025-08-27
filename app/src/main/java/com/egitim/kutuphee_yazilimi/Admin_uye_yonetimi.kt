package com.egitim.kutuphee_yazilimi

import Adapter.UyeYonetimAdapter
import DAO.UyeDao
import Database.KutuphaneDatabase
import Entity.Uye
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Admin_uye_yonetimi : AppCompatActivity() {

    private lateinit var uyeDao: UyeDao
    private lateinit var adapter: UyeYonetimAdapter
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyListMessage: TextView
    private lateinit var ÜyeEklemeFab: FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_uye_yonetimi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        searchView = findViewById(R.id.searchViewUyeler)
        recyclerView = findViewById(R.id.recyclerViewUyeler)
        emptyListMessage = findViewById(R.id.emptyListMessage)
        ÜyeEklemeFab = findViewById(R.id.fabAddUye)

        uyeDao = KutuphaneDatabase.getDatabase(this).UyeDao()

        adapter = UyeYonetimAdapter(
            onUpdateClicked = { uye ->
                val intent = Intent(this, Uye_yonetimi_uye_guncelleme::class.java)
                intent.putExtra("uye_id", uye.id)
                startActivity(intent)
            },
            onDeleteClicked = { uye ->
                Silmeyi_Onaylama(uye)
            }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadUyeler()
        setupSearchView()

        // FAB butonuna tıklama dinleyicisini ekliyoruz
        ÜyeEklemeFab.setOnClickListener {
            val intent = Intent(this, Uye_Yonetimi_uye_ekleme::class.java)
            startActivity(intent)
        }
    }

    private fun loadUyeler() {
        lifecycleScope.launch {
            uyeDao.tumUyeleriGetir().collectLatest { uyeListesi ->
                adapter.submitList(uyeListesi)
                emptyListMessage.visibility = if (uyeListesi.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Uyeleri_Filtrele(newText)
                return true
            }
        })
    }

    private fun Uyeleri_Filtrele(query: String?) {
        lifecycleScope.launch(Dispatchers.IO) {
            uyeDao.uyeAra(query.orEmpty()).collectLatest { filteredList ->
                withContext(Dispatchers.Main) {
                    adapter.submitList(filteredList)
                    emptyListMessage.visibility = if (filteredList.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        }
    }

    private fun Silmeyi_Onaylama(uye: Uye) {
        AlertDialog.Builder(this)
            .setTitle("Üyeyi Sil")
            .setMessage("'${uye.uyeadi} ${uye.uyesoyadi}' adlı üyeyi silmek istediğinizden emin misiniz?")
            .setPositiveButton("Sil") { _, _ ->
                ÜyeSil(uye)
            }
            .setNegativeButton("İptal", null).show()
    }

    private fun ÜyeSil(uye: Uye) {
        lifecycleScope.launch(Dispatchers.IO) {
            uyeDao.uyeSil(uye)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@Admin_uye_yonetimi, "Üye başarıyla silindi.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}