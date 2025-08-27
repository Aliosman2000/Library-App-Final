package Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "okunmus_kitaplar")
data class OkunmusKitapEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val kitapadi: String,
    val yazar: String,
    val category: String,
    val isbn: String?,
    val userId: String
)