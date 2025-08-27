package com.egitim.kutuphee_yazilimi

import DAO.KitapDao
import Database.KutuphaneDatabase
import Entity.Kitap
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Kitap_yonetimi_kitap_ekleme_activity : AppCompatActivity() {

    private lateinit var kitapDao: KitapDao
    private lateinit var editTextKitapAdi: EditText
    private lateinit var editTextYazar: EditText
    private lateinit var editTextCategory: EditText
    private lateinit var editTextISBN: EditText
    private lateinit var editTextStok: EditText
    private lateinit var buttonEkle: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kitap_yonetimi_kitap_ekleme)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTextKitapAdi = findViewById(R.id.editTextKitapAdi)
        editTextYazar = findViewById(R.id.editTextYazar)
        editTextCategory = findViewById(R.id.editTextCategory)
        editTextISBN = findViewById(R.id.editTextISBN)
        editTextStok = findViewById(R.id.editTextStok)
        buttonEkle = findViewById(R.id.buttonEkle)


        kitapDao = KutuphaneDatabase.getDatabase(this).KitapDao()

        buttonEkle.setOnClickListener {
            addBookToDatabase()
        }
    }

    private fun addBookToDatabase() {
        val kitapAdi = editTextKitapAdi.text.toString().trim()
        val yazar = editTextYazar.text.toString().trim()
        val category = editTextCategory.text.toString().trim()
        val isbn = editTextISBN.text.toString().trim()


        if (kitapAdi.isEmpty() || yazar.isEmpty() || category.isEmpty() || isbn.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun.", Toast.LENGTH_SHORT).show()
            return
        }



        lifecycleScope.launch(Dispatchers.IO) {

            val varolankitap = kitapDao.kitapgetirIsbn(isbn)

            if (varolankitap != null) {

                varolankitap.stokadedi += 1


                kitapDao.kitapGuncelle(varolankitap)

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@Kitap_yonetimi_kitap_ekleme_activity, "Kitap stoğu 1 artırıldı!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            else
            {
                val yeniKitap = Kitap(
                    kitapadi = kitapAdi,
                    yazar = yazar,
                    stokadedi = 1,
                    isbn = isbn,
                    category = category,
                )

                kitapDao.kitapEkle(yeniKitap)

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@Kitap_yonetimi_kitap_ekleme_activity, "Yeni kitap başarıyla eklendi!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}