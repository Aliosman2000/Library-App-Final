package com.egitim.kutuphee_yazilimi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import DAO.KitapDao
import Database.KutuphaneDatabase
import Entity.Kitap
import android.annotation.SuppressLint
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext




class Kitap_yonetimi_kitap_guncelleme_ekrani : AppCompatActivity() {

    private lateinit var kitapDao: KitapDao
    private var kitapToUpdate: Kitap? = null

    private lateinit var editTextKitapAdi: EditText
    private lateinit var editTextYazar: EditText
    private lateinit var editTextCategory: EditText
    private lateinit var editTextISBN: EditText
    private lateinit var editTextStok: EditText
    private lateinit var buttonGuncelle: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kitap_yonetimi_kitap_guncelleme_ekrani)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.kitap_guncelleme)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        editTextKitapAdi = findViewById(R.id.editTextKitapAdi)
        editTextYazar = findViewById(R.id.editTextYazar)
        editTextCategory = findViewById(R.id.editTextCategory)
        editTextISBN = findViewById(R.id.editTextISBN)
        editTextStok = findViewById(R.id.editTextStok)
        buttonGuncelle = findViewById(R.id.buttonGuncelle)



        kitapDao = KutuphaneDatabase.getDatabase(this).KitapDao()

        val kitapId = intent.getIntExtra("kitap_id", -1)

        if (kitapId != -1) {
            lifecycleScope.launch(Dispatchers.IO) {
                kitapToUpdate = kitapDao.kitapGetirId(kitapId)
                withContext(Dispatchers.Main) {
                    if (kitapToUpdate != null) {

                        editTextKitapAdi.setText(kitapToUpdate!!.kitapadi)
                        editTextYazar.setText(kitapToUpdate!!.yazar)
                        editTextCategory.setText(kitapToUpdate!!.category)
                        editTextISBN.setText(kitapToUpdate!!.isbn)
                        editTextStok.setText(kitapToUpdate!!.stokadedi.toString())
                    }
                    else
                    {
                        Toast.makeText(this@Kitap_yonetimi_kitap_guncelleme_ekrani, "Kitap bulunamadı.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
        else
        {
            Toast.makeText(this, "Geçersiz kitap ID'si.", Toast.LENGTH_SHORT).show()
            finish()
        }

        buttonGuncelle.setOnClickListener {
            veritabanındakitapguncelle()
        }
    }

    private fun veritabanındakitapguncelle() {

        val mevcutkitap = kitapToUpdate ?: run {
            Toast.makeText(this, "Güncellenecek kitap bulunamadı.", Toast.LENGTH_SHORT).show()
            return
        }


        val updatedKitapAdi = editTextKitapAdi.text.toString().trim()
        val updatedYazar = editTextYazar.text.toString().trim()
        val updatedCategory = editTextCategory.text.toString().trim()
        val updatedISBN = editTextISBN.text.toString().trim()
        val updatedStokAdedi = editTextStok.text.toString().toIntOrNull()


        if (updatedKitapAdi.isEmpty() || updatedYazar.isEmpty() || updatedCategory.isEmpty() || updatedISBN.isEmpty() || updatedStokAdedi == null) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun.", Toast.LENGTH_SHORT).show()
            return
        }


        val updatedKitap = mevcutkitap.copy(kitapadi = updatedKitapAdi, yazar = updatedYazar, isbn = updatedISBN, category = updatedCategory, stokadedi = updatedStokAdedi
        )

        lifecycleScope.launch(Dispatchers.IO) {
            kitapDao.kitapGuncelle(updatedKitap)

            withContext(Dispatchers.Main) {
                Toast.makeText(this@Kitap_yonetimi_kitap_guncelleme_ekrani, "Kitap başarıyla güncellendi!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}