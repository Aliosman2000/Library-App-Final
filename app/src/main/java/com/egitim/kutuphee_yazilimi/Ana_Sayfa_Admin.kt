package com.egitim.kutuphee_yazilimi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ana_Sayfa_Admin : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ana_sayfa_admin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Profile_root_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun Admin_Kitap_Yon_Buton(view: View) {
        val intent = Intent(this@Ana_Sayfa_Admin, KitapYonetimActivity::class.java)
        startActivity(intent)
    }
    fun Admin_uye_Yon_button(view: View) {
        val intent = Intent(this@Ana_Sayfa_Admin, Admin_uye_yonetimi::class.java)
        startActivity(intent)
    }

    fun Cikis(view: View) {
        val intent = Intent(this@Ana_Sayfa_Admin, MainActivity::class.java)
        startActivity(intent)
        Toast.makeText(this@Ana_Sayfa_Admin, "Çıkış Yaptınız ",Toast.LENGTH_SHORT).show()
    }
}