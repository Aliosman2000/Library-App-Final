package com.egitim.kutuphee_yazilimi


import Database.KutuphaneDatabase
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
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private lateinit var add: EditText
private lateinit var soyadd: EditText
private lateinit var epostaa: EditText
private lateinit var kullaniciAdii: EditText
private lateinit var sifree: EditText
private lateinit var sifretekrarr: EditText
private lateinit var kaydolButonuu: Button
private lateinit var hizmetSartlariCheckBox: CheckBox


private lateinit var db: KutuphaneDatabase

// UI elemanları için değişkenler tanımlıyoruz.
private lateinit var Kullanici: EditText
private lateinit var Sifre: EditText
private lateinit var btnLogin: Button







class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Profile_root_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }



        add = findViewById(R.id.ad)
        soyadd = findViewById(R.id.soyad)
        epostaa = findViewById(R.id.eposta)
        kullaniciAdii = findViewById(R.id.kullaniciadi)
        sifree = findViewById(R.id.sifre)
        sifretekrarr = findViewById(R.id.sifretekrar)
        kaydolButonuu = findViewById(R.id.kaydolButonu)
        hizmetSartlariCheckBox = findViewById(R.id.hizmetsartlaricheckbox)




        db = KutuphaneDatabase.getDatabase(this)


        Kullanici = findViewById(R.id.Kullanici)
        Sifre = findViewById(R.id.Sifree)
        btnLogin = findViewById(R.id.buttonGiris)


    }


    fun kayidoll(view: View) {
        val intent = Intent(this@MainActivity, Kayit::class.java)
        startActivity(intent)

        if (!hizmetSartlariCheckBox.isChecked) {
            Toast.makeText(this, "Lütfen hizmet şartlarını kabul ediniz!", Toast.LENGTH_SHORT).show()
        }

        else if (add.length() == 0 || soyadd.length() == 0 || epostaa.length() == 0 || kullaniciAdii.length() == 0 || sifree.length() == 0 || sifretekrarr.length() == 0) {
            Toast.makeText(this, "Lütfen tüm alanları doldurunuz!", Toast.LENGTH_SHORT).show()
        }

        else if (sifree != sifretekrarr) {
            Toast.makeText(this, "Girilen şifreler uyuşmuyor!", Toast.LENGTH_SHORT).show()
            sifree.text.clear()
            sifretekrarr.text.clear()
        }

        else {
            Toast.makeText(this, "Kayıt Başarılı!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Kayit_Basarili::class.java)
            startActivity(intent)
            finish()
        }
    }




    fun btnsunut(view: View) {
        val intent = Intent(this, Sifre_Unuttum::class.java)
        startActivity(intent)
    }





    fun btngiris(view: View) {

        val username = Kullanici.text.toString()
        val password = Sifre.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Lütfen kullanıcı adı ve şifre giriniz", Toast.LENGTH_SHORT).show()
            return
        }

        val db = KutuphaneDatabase.getDatabase(this)
        val kullaniciDao = db.kullaniciDao()

        lifecycleScope.launch(Dispatchers.IO) {
            try
            {
                val user = kullaniciDao.kullaniciKontrol(username, password)

                withContext(Dispatchers.Main) {
                    if (user == null) {
                        Toast.makeText(this@MainActivity, "Kullanıcı bilgisi alınamadı", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        if (user.rol == "admin")
                        {
                            val intent = Intent(this@MainActivity, Ana_Sayfa_Admin::class.java)
                            intent.putExtra("id", user.id)
                            startActivity(intent)
                        }
                        else
                        {
                            val intent = Intent(this@MainActivity, Ana_Sayfa_Kullanici::class.java)
                            intent.putExtra("id", user.id)
                            startActivity(intent)
                        }
                        finish()
                    }
                }
            }
            catch (e: Exception)
            {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Bir hata oluştu: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}




