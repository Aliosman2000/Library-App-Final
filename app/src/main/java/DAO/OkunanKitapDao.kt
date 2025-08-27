package DAO

import Entity.OkunmusKitapEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OkunanKitapDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(kitap: OkunmusKitapEntity)


    @Query("SELECT * FROM okunmus_kitaplar WHERE userId = :kullaniciId ORDER BY kitapAdi ASC")
    fun Kullaniciidokunankitaplarigetir(kullaniciId: String): Flow<List<OkunmusKitapEntity>>


    @Query("DELETE FROM okunmus_kitaplar WHERE id = :kitapId")
    suspend fun idegoresilokunankitap(kitapId: Int)

    @Query("SELECT * FROM okunmus_kitaplar ORDER BY kitapAdi ASC")
     fun tumKitaplariGetir(): Flow<List<OkunmusKitapEntity>>
}

