package com.egitim.kutuphee_yazilimi

import DAO.UyeDao
import Database.KutuphaneDatabase
import Entity.Uye
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Uye_Yonetimi_uye_ekleme : AppCompatActivity() {

    private lateinit var uyeDao: UyeDao
    private lateinit var etAdi: EditText
    private lateinit var etSoyadi: EditText
    private lateinit var etEposta: EditText
    private lateinit var etSifre: EditText
    private lateinit var etUyeNo: EditText
    private lateinit var etRol: EditText
    private lateinit var btnKaydet: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_uye_yonetimi_uye_ekleme)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Admin_Uye_Guncelleme)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // UI bileşenlerini bağla
        etAdi = findViewById(R.id.UyeAdiEditText)
        etSoyadi = findViewById(R.id.UyeSoyadiEditText)
        etEposta = findViewById(R.id.EpostaEditText)
        etSifre = findViewById(R.id.SifreEditText)
        etUyeNo = findViewById(R.id.UyeNoEditText)
        etRol = findViewById(R.id.UyeRoluEditText)
        btnKaydet = findViewById(R.id.guncelleButton) // Lütfen buton ID'nizi kontrol edin

        val db = KutuphaneDatabase.getDatabase(this)
        uyeDao = db.UyeDao()

        btnKaydet.setOnClickListener {
            uyeKaydet()
        }
    }

    private fun uyeKaydet() {
        val uyeAdi = etAdi.text.toString().trim()
        val uyeSoyadi = etSoyadi.text.toString().trim()
        val eposta = etEposta.text.toString().trim()
        val sifre = etSifre.text.toString().trim()
        val uyeNo = etUyeNo.text.toString().trim()
        val uyeRolu = etRol.text.toString().trim()

        // Alanların tek tek kontrol edilmesi
        if (uyeAdi.isEmpty()) {
            Toast.makeText(this, "Lütfen Adı alanını doldurun!", Toast.LENGTH_SHORT).show()
            return
        }
        if (uyeSoyadi.isEmpty()) {
            Toast.makeText(this, "Lütfen Soyadı alanını doldurun!", Toast.LENGTH_SHORT).show()
            return
        }
        if (eposta.isEmpty()) {
            Toast.makeText(this, "Lütfen E-posta alanını doldurun!", Toast.LENGTH_SHORT).show()
            return
        }
        if (sifre.isEmpty()) {
            Toast.makeText(this, "Lütfen Şifre alanını doldurun!", Toast.LENGTH_SHORT).show()
            return
        }
        if (uyeNo.isEmpty()) {
            Toast.makeText(this, "Lütfen Üye No alanını doldurun!", Toast.LENGTH_SHORT).show()
            return
        }
        if (uyeRolu.isEmpty()) {
            Toast.makeText(this, "Lütfen Rol alanını doldurun!", Toast.LENGTH_SHORT).show()
            return
        }

        val yeniUye = Uye(uyeadi = uyeAdi, uyesoyadi = uyeSoyadi, eposta = eposta, sifre = sifre, uyeno = uyeNo, uyerolu = uyeRolu)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                uyeDao.uyeEkle(yeniUye)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@Uye_Yonetimi_uye_ekleme, "Üye başarıyla kaydedildi.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            catch (e: Exception)
            {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@Uye_Yonetimi_uye_ekleme, "Kayıt sırasında bir hata oluştu: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
