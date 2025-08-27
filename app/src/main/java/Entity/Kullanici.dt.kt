package Entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kullanicilar")
data class Kullanici(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ad: String,
    val soyad: String,
    val eposta: String,
    val kullaniciAdi: String,
    val sifre: String,
    val rol: String,
    )