package com.egitim.kutuphee_yazilimi


import Adapter.KitapAdapter
import DAO.KitapDao
import Database.KutuphaneDatabase
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



private lateinit var searchView: SearchView
private lateinit var recyclerView: RecyclerView
private lateinit var kitapDao: KitapDao
private lateinit var adapter: KitapAdapter
private lateinit var checkYazar : CheckBox
private lateinit var checkKategori : CheckBox
private lateinit var kitapAraButton : Button
private lateinit var tvBosMesaj: TextView


class Kitap_Arama_Ekrani : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kitap_arama_ekrani)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        searchView = findViewById(R.id.searchview)
        checkYazar = findViewById(R.id.ygsırala)
        checkKategori = findViewById(R.id.category)
        kitapAraButton = findViewById(R.id.kitapAra)
        recyclerView = findViewById(R.id.recyclerviewkitaparamasonuc)
        tvBosMesaj = findViewById(R.id.tvBosMesaj)

        adapter = KitapAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        kitapDao = KutuphaneDatabase.getDatabase(this).KitapDao()

        kitapAraButton.setOnClickListener {
            kitaparamabutonu()
        }
    }

    private fun kitaparamabutonu() {
        val query = "%${searchView.query.toString().trim()}%"

        CoroutineScope(Dispatchers.IO).launch {
            val kitapList = when {
                checkYazar.isChecked -> kitapDao.yazarAdinaGoreAra(query).first()
                checkKategori.isChecked -> kitapDao.kategoriyeGoreAra(query).first()
                else -> kitapDao.kitapAdinaGoreAra(query).first()
            }

            withContext(Dispatchers.Main) {
                adapter.setData(kitapList)
                tvBosMesaj.visibility =
                    if (kitapList.isEmpty()) {
                        View.VISIBLE
                    }
                    else
                    {
                        View.GONE
                    }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@Kitap_Arama_Ekrani, Ana_Sayfa_Kullanici::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }
}

