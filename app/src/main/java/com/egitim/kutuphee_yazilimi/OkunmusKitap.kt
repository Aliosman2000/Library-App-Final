package com.egitim.kutuphee_yazilimi
import Adapter.OkunmusKitapAdapter
import DAO.KitapDao
import DAO.OkunanKitapDao
import Database.KutuphaneDatabase
import Entity.OkunmusKitapEntity
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class OkunmusKitap : AppCompatActivity() {


    private lateinit var kitapDao: OkunanKitapDao
    private lateinit var adapter: OkunmusKitapAdapter
    private val okunanKitaplar = mutableListOf<OkunmusKitapEntity>()
    private var userId: Int = -1


    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_okunmus_kitap)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Profile_root_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        kitapDao = KutuphaneDatabase.getDatabase(this).okunanKitapDao() as OkunanKitapDao

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        adapter = OkunmusKitapAdapter(okunanKitaplar)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val okunanKitapSayisiTextView = findViewById<TextView>(R.id.okudugukitapsayisi)


        lifecycleScope.launch {
            kitapDao.tumKitaplariGetir().collect { books ->
                okunanKitaplar.clear()
                okunanKitaplar.addAll(books)
                adapter.notifyDataSetChanged()
                okunanKitapSayisiTextView.text = "Okuduğum Kitap Sayısı: ${okunanKitaplar.size}"
            }
        }
    }
}