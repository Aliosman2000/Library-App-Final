package Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "İade")
data class İade(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val uyeid : Int,
    val kitapId : Int,
    val odunc_tarihi : Long,
    val iade_tarihi: Long?,
    val iadekontrol : String
)