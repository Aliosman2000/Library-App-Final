package com.egitim.kutuphee_yazilimi

import DAO.KullaniciDao
import Database.KutuphaneDatabase
import Entity.Kullanici
import ViewModel.KullaniciViewModel
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
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



class Kayit : AppCompatActivity() {

    private lateinit var adEditText: EditText
    private lateinit var soyadEditText: EditText
    private lateinit var epostaEditText: EditText
    private lateinit var kullaniciAdiEditText: EditText
    private lateinit var sifreEditText: EditText
    private lateinit var sifreTekrarEditText: EditText
    private lateinit var kaydolButton: Button
    private lateinit var hizmetSartlariCheckBox: CheckBox

    private lateinit var kullaniciDao: KullaniciDao
    private lateinit var kullaniciViewModel: KullaniciViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kayit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Profile_root_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adEditText = findViewById(R.id.ad)
        soyadEditText = findViewById(R.id.soyad)
        epostaEditText = findViewById(R.id.eposta)
        kullaniciAdiEditText = findViewById(R.id.kullaniciadi)
        sifreEditText = findViewById(R.id.sifre)
        sifreTekrarEditText = findViewById(R.id.sifretekrar)
        kaydolButton = findViewById(R.id.kaydolButonu)
        hizmetSartlariCheckBox = findViewById(R.id.hizmetsartlaricheckbox)

        val db = KutuphaneDatabase.getDatabase(this)
        kullaniciDao = db.kullaniciDao()



        kaydolButton.setOnClickListener {
            val ad = adEditText.text.toString().trim()
            val soyad = soyadEditText.text.toString().trim()
            val eposta = epostaEditText.text.toString().trim()
            val kullaniciAdi = kullaniciAdiEditText.text.toString().trim()
            val sifre = sifreEditText.text.toString()
            val sifreTekrar = sifreTekrarEditText.text.toString()

            if (!hizmetSartlariCheckBox.isChecked) {
                Toast.makeText(this, "Lütfen hizmet şartlarını kabul ediniz!", Toast.LENGTH_SHORT).show()
            }
            else if (ad.isEmpty() || soyad.isEmpty() || eposta.isEmpty() || kullaniciAdi.isEmpty() || sifre.isEmpty() || sifreTekrar.isEmpty())
            {
                Toast.makeText(this, "Lütfen tüm alanları doldurunuz!", Toast.LENGTH_SHORT).show()
            }
            else if (sifre != sifreTekrar)
            {
                Toast.makeText(this, "Girilen şifreler uyuşmuyor!", Toast.LENGTH_SHORT).show()
                sifreEditText.text.clear()
                sifreTekrarEditText.text.clear()
            }

            else
            {
                CoroutineScope(Dispatchers.IO).launch{
                    val kullaniciVarMi = kullaniciDao.kullaniciVarMi(kullaniciAdi)

                    withContext(Dispatchers.Main) {
                        if (kullaniciVarMi)
                        {
                            Toast.makeText(this@Kayit, "Bu kullanıcı adı zaten alınmış!", Toast.LENGTH_LONG).show()
                        }
                        else
                        {
                            val yeniKullanici = Kullanici(ad = ad, soyad = soyad, eposta = eposta, kullaniciAdi = kullaniciAdi, sifre = sifre, rol = "Kullanıcı")
                            kullaniciDao.insertkullanici(yeniKullanici)

                            // Tüm UI işlemlerini veritabanı işlemi tamamlandıktan sonra çalıştır
                            Toast.makeText(this@Kayit, "Kayıt Başarılı!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@Kayit, Kayit_Basarili::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
    }

    // Geri tuşuna basıldığında uygulama kapanmasın, ana ekrana dönsün
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
