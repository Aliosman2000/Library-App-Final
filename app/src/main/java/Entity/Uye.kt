package Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "uyeler")
data class Uye(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var uyeadi: String,
    var uyesoyadi: String,
    var eposta: String,
    var sifre: String,
    var uyeno: String,
    var uyerolu: String,
    var aktif_mi: Boolean = true
)