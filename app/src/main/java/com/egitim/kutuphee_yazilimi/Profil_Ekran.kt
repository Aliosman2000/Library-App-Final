package com.egitim.kutuphee_yazilimi
import Database.KutuphaneDatabase
import Repository.KullaniciRepository
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Profil_Ekran : AppCompatActivity() {

    private lateinit var tvAd: EditText
    private lateinit var tvSoyad: EditText
    private lateinit var tvEposta: EditText
    private lateinit var tvKullaniciAdi: EditText
    private lateinit var tvSifre: EditText
    private lateinit var btnCikisYap: Button

    private lateinit var repository: KullaniciRepository

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profil_ekran)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Profile_root_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        val database = KutuphaneDatabase.getDatabase(this)
        repository = KullaniciRepository(database.kullaniciDao())


        tvAd = findViewById(R.id.etİsim)
        tvSoyad = findViewById(R.id.etSoyad)
        tvEposta = findViewById(R.id.etEposta)
        tvKullaniciAdi = findViewById(R.id.etKullaniciAdi)
        tvSifre = findViewById(R.id.etSifree)
        btnCikisYap = findViewById(R.id.profiloncekisayfa)


        val userId = intent.getIntExtra("id", -1)

        if (userId != -1) {
            loadUserProfile(userId)
        }
        else
        {
            Toast.makeText(this, "Kullanıcı bilgisi alınamadı.", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnCikisYap.setOnClickListener {
            Toast.makeText(this, "Oturum sonlandırıldı.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun loadUserProfile(userId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val user = repository.kullaniciId(userId)
            if (user != null) {
                // Bilgileri ekrana yerleştir
                tvAd.setText(user.ad)
                tvSoyad.setText(user.soyad)
                tvEposta.setText(user.eposta)
                tvKullaniciAdi.setText(user.kullaniciAdi)
                tvSifre.setText(user.sifre)
            }
            else
            {
                Toast.makeText(this@Profil_Ekran, "Kullanıcı bulunamadı.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}