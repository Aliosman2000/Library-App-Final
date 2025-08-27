package Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kitaplar")
data class Kitap(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val kitapadi: String,
    val yazar: String,
    val category: String,
    var stokadedi: Int = 1,
    val isbn: String,
)