package com.egitim.kutuphee_yazilimi


import Database.KutuphaneDatabase
import Entity.OkunmusKitapEntity
import Repository.OkunanKitapRepository
import ViewModel.OkunanKitapViewModel
import ViewModel.OkunanKitapViewModelFactory
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider


class Kitap_Ekle_Activity : AppCompatActivity() {


    private var userId: Int = -1


    private lateinit var okunanKitapViewModel: OkunanKitapViewModel

    private lateinit var kitapAdiEditText: EditText
    private lateinit var yazarAdiEditText: EditText
    private lateinit var kategoriEditText: EditText
    private lateinit var isbnEditText: EditText
    private lateinit var kitapEkleButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kitap_ekle_ekran)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Profile_root_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val repository = OkunanKitapRepository(KutuphaneDatabase.getDatabase(applicationContext).okunanKitapDao())
        val viewModelFactory = OkunanKitapViewModelFactory(repository)
        okunanKitapViewModel = ViewModelProvider(this, viewModelFactory).get(OkunanKitapViewModel::class.java)

        // XML layout'tan elemanları ID'lerine göre bulup değişkenlere atıyoruz.
        kitapAdiEditText = findViewById(R.id.kitapisim)
        yazarAdiEditText = findViewById(R.id.yazarisim)
        kategoriEditText = findViewById<EditText>(R.id.kategori)
        isbnEditText = findViewById<EditText>(R.id.ısbn)
        kitapEkleButton = findViewById<Button>(R.id.kitapeklee)


        kitapEkleButton.setOnClickListener {
            kitabiKaydet()
        }
    }

    private fun kitabiKaydet() {

        val kitapAdi = kitapAdiEditText.text.toString().trim()
        val yazar = yazarAdiEditText.text.toString().trim()
        val kategori = kategoriEditText.text.toString().trim()
        val isbn = isbnEditText.text.toString().trim()


        if (kitapAdi.isEmpty() || yazar.isEmpty() || kategori.isEmpty() || isbn.isEmpty()) {

            Toast.makeText(this, "Lütfen tüm alanları doldurun.", Toast.LENGTH_SHORT).show()
            return
        }


        val yeniKitap = OkunmusKitapEntity(
            kitapadi = kitapAdi,
            yazar = yazar,
            category = kategori,
            isbn = isbn,
            userId = userId.toString()
            // OkunmusKitapEntity'nizde varsa 'id' alanını da buraya ekleyebilirsiniz.
            // Örn: id = 0
        )


        okunanKitapViewModel.insertOkunanKitap(yeniKitap)

        // İşlem tamamlandıktan sonra kullanıcıya başarı mesajı gösterebilirsiniz.
        Toast.makeText(this, "Kitap başarıyla kaydedildi!", Toast.LENGTH_SHORT).show()


        kitapAdiEditText.text.clear()
        yazarAdiEditText.text.clear()
        kategoriEditText.text.clear()
        isbnEditText.text.clear()
    }
}
