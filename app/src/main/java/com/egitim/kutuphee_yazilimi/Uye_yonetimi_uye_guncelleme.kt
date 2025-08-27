package com.egitim.kutuphee_yazilimi


import DAO.UyeDao
import Database.KutuphaneDatabase
import Entity.Uye
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


class Uye_yonetimi_uye_guncelleme : AppCompatActivity() {

    private var mevcutUye: Uye? = null

    private lateinit var etAdi: EditText
    private lateinit var etSoyadi: EditText
    private lateinit var etEposta: EditText
    private lateinit var etSifre: EditText
    private lateinit var etUyeNo: EditText
    private lateinit var etRol: EditText
    private lateinit var btnGuncelle: Button
    private lateinit var uyeDao: UyeDao

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_uye_yonetimi_uye_guncelleme)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.uye_yon_guncelleme)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etAdi = findViewById(R.id.etAdi)
        etSoyadi = findViewById(R.id.etSoyadi)
        etEposta = findViewById(R.id.etEposta)
        etSifre = findViewById(R.id.etSifre)
        etUyeNo = findViewById(R.id.etUyeNo)
        etRol = findViewById(R.id.etRol)
        btnGuncelle = findViewById(R.id.btnGuncelle)

        uyeDao = KutuphaneDatabase.getDatabase(this).UyeDao()

        val uyeId = intent.getIntExtra("uye_id", -1)
        if (uyeId == -1) {
            Toast.makeText(this, "Geçersiz üye ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {
            mevcutUye = uyeDao.uyeGetirId(uyeId)
            withContext(Dispatchers.Main) {
                if (mevcutUye != null) {
                    etAdi.setText(mevcutUye?.uyeadi)
                    etSoyadi.setText(mevcutUye?.uyesoyadi)
                    etEposta.setText(mevcutUye?.eposta)
                    etSifre.setText(mevcutUye?.sifre)
                    etUyeNo.setText(mevcutUye?.uyeno)
                    etRol.setText(mevcutUye?.uyerolu)
                }
                else
                {
                    Toast.makeText(this@Uye_yonetimi_uye_guncelleme, "Üye bulunamadı", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

        btnGuncelle.setOnClickListener {
            val guncelUye = mevcutUye?.copy(uyeadi = etAdi.text.toString().trim(), uyesoyadi = etSoyadi.text.toString().trim(), eposta = etEposta.text.toString().trim(), sifre = etSifre.text.toString().trim(), uyeno = etUyeNo.text.toString().trim(), uyerolu = etRol.text.toString().trim()) ?: return@setOnClickListener

            lifecycleScope.launch(Dispatchers.IO) {
                uyeDao.uyeGuncelle(guncelUye)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@Uye_yonetimi_uye_guncelleme, "Üye başarıyla güncellendi", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}