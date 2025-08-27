package com.egitim.kutuphee_yazilimi
import DAO.KullaniciDao
import Database.KutuphaneDatabase
import android.annotation.SuppressLint
import android.content.Intent
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

class Sifre_Unuttum : AppCompatActivity() {


    private lateinit var editTextUsername: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var gonderSifreButton: Button

    private lateinit var kullaniciDao: KullaniciDao

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sifre_unuttum)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Profile_root_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = KutuphaneDatabase.getDatabase(this)
        kullaniciDao = db.kullaniciDao()

        // findViewById ile UI bileşenlerini bul
        editTextUsername = findViewById(R.id.KullaniciAdiSu)
        editTextEmail = findViewById(R.id.Eposta)
        gonderSifreButton = findViewById(R.id.btnGonder) // Buton ID'sini kontrol et

        // Butona bir OnClickListener tanımla
        gonderSifreButton.setOnClickListener {
            GonderSifre(it)
        }
    }

    fun GonderSifre(view: View) {
       val kullaniciAdi = editTextUsername.text.toString().trim()
        val eposta = editTextEmail.text.toString().trim()

        if (kullaniciAdi.isEmpty() || eposta.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun.", Toast.LENGTH_SHORT).show()
            return
        }


        CoroutineScope(Dispatchers.IO).launch {
            val kullanici = kullaniciDao.kullaniciKontrol(kullaniciAdi, eposta)

            withContext(Dispatchers.Main) {
                if (kullanici != null) {
                    // Eşleşme bulundu, yeni şifre gönderme ekranına git
                    Toast.makeText(this@Sifre_Unuttum, "Doğrulama başarılı.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@Sifre_Unuttum, SifreGonderBasarili::class.java)
                    startActivity(intent)
                }
                else
                {
                    val kullaniciAdiVarMi = kullaniciDao.kullaniciVarMi(kullaniciAdi)

                    if (kullaniciAdiVarMi)
                    {
                        // Kullanıcı adı var ama eposta yanlış
                        Toast.makeText(this@Sifre_Unuttum, "Girilen e-posta adresi yanlış.", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(this@Sifre_Unuttum, "Böyle bir kullanıcı adı bulunamadı.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}