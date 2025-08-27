package com.egitim.kutuphee_yazilimi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


var userId: Int = -1


class Ana_Sayfa_Kullanici : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.ana_sayfa_kullanici)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Profile_root_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userId = intent.getIntExtra("id", -1)  // anahtar "id" olmalı

        if (userId == -1) {
            Toast.makeText(this, "Kullanıcı bilgisi alınamadı.", Toast.LENGTH_SHORT).show()
            finish()
        }

        val btnProfil: Button = findViewById(R.id.Profil)
        btnProfil.setOnClickListener {
            val intent = Intent(this, Profil_Ekran::class.java)
            intent.putExtra("id", userId)  // ID'yi Profil_Ekran'a gönder
            startActivity(intent)
        }
    }






    fun kitapekle(view: View) {

        val intent = Intent(this@Ana_Sayfa_Kullanici, Kitap_Ekle_Activity::class.java)
        startActivity(intent)
    }


    fun okunmuşkitap(view: View) {

        val intent = Intent(this, OkunmusKitap::class.java)
        startActivity(intent)
    }

    fun profile(view: View) {

            val userId = intent.getIntExtra("id", -1) // anahtar "id"

            if (userId == -1) {
                Toast.makeText(this, "Kullanıcı bilgisi alınamadı.", Toast.LENGTH_SHORT).show()
                return
            }

            val intent = Intent(this, Profil_Ekran::class.java)
            intent.putExtra("id", userId)
            startActivity(intent)
    }


    fun btnKitapAra(view: View) {
        val intent = Intent(this@Ana_Sayfa_Kullanici, Kitap_Arama_Ekrani::class.java)
        startActivity(intent)

    }

    fun cikis(view: View) {
        val intent = Intent(this@Ana_Sayfa_Kullanici, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}