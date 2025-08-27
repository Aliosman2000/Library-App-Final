package com.egitim.kutuphee_yazilimi


import Adapter.KitapYonetimAdapter
import DAO.KitapDao
import Database.KutuphaneDatabase
import Entity.Kitap
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

class KitapYonetimActivity : AppCompatActivity() {

    private lateinit var kitapDao: KitapDao
    private lateinit var adapter: KitapYonetimAdapter
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyListMessage: TextView
    private lateinit var fabAddKitap: FloatingActionButton




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kitap_yonetim)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        searchView = findViewById(R.id.searchView)
        recyclerView = findViewById(R.id.recyclerViewKitaplar)
        emptyListMessage = findViewById(R.id.emptyListMessage)
        fabAddKitap = findViewById(R.id.fabAddKitap)


        kitapDao = KutuphaneDatabase.getDatabase(this).KitapDao()


        adapter = KitapYonetimAdapter(
            onUpdateClicked = { kitap ->
                val intent = Intent(this@KitapYonetimActivity, Kitap_yonetimi_kitap_guncelleme_ekrani::class.java)
                intent.putExtra("kitap_id", kitap.id) // Kitap nesnesini Intent'e ekle
                startActivity(intent)
            },
            onDeleteClicked = { kitap ->
                silmeyiOnaylama(kitap)
            }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Kitapları veritabanından yükle ve gözlemle
        loadBooks()

        // Arama çubuğu için dinleyici ayarla
        setupSearchView()

        // Ekleme butonu için dinleyici ayarla
        fabAddKitap.setOnClickListener {
            val intent = Intent(this@KitapYonetimActivity, Kitap_yonetimi_kitap_ekleme_activity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Yeni kitap ekleme ekranı açılıyor...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadBooks() {
        lifecycleScope.launch {
            // Düzeltme: Flow'u gözlemlemek için collectLatest kullan
            kitapDao.tumKitaplariGetir().collectLatest { kitapListesi ->
                adapter.submitList(kitapListesi)
                emptyListMessage.visibility = if (kitapListesi.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                kitaplari_filtrele(newText)
                return true
            }
        })
    }

    private fun kitaplari_filtrele(query: String?) {
        lifecycleScope.launch(Dispatchers.IO) {
            // Düzeltme: Yeni arama fonksiyonunu kullan
            kitapDao.aramaYap(query.orEmpty()).collectLatest { filteredList ->
                withContext(Dispatchers.Main) {
                    adapter.submitList(filteredList)
                    emptyListMessage.visibility = if (filteredList.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        }
    }

    private fun silmeyiOnaylama(kitap: Kitap) {
        AlertDialog.Builder(this)
            .setTitle("Kitabı Sil")
            .setMessage("'${kitap.kitapadi}' adlı kitabı silmek istediğinizden emin misiniz?")
            .setPositiveButton("Sil") { _, _ ->
                kitabı_sil(kitap)
            }
            .setNegativeButton("İptal", null)
            .show()
    }

    private fun kitabı_sil(kitap: Kitap) {
        lifecycleScope.launch(Dispatchers.IO) {
            kitapDao.kitapSil(kitap)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@KitapYonetimActivity, "'${kitap.kitapadi}' silindi.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}