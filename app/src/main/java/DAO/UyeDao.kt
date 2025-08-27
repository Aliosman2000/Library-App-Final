package DAO

import Entity.Uye
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UyeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun uyeEkle(uye: Uye)

    @Delete
    suspend fun uyeSil(uye: Uye)

    @Update
    suspend fun uyeGuncelle(uye: Uye)

    @Query("SELECT * FROM uyeler ORDER BY uyeadi ASC")
    fun tumUyeleriGetir(): Flow<List<Uye>>

    @Query("SELECT * FROM uyeler WHERE uyeadi LIKE '%' || :query || '%' OR uyesoyadi LIKE '%' || :query || '%' OR eposta LIKE '%' || :query || '%' COLLATE NOCASE")
    fun uyeAra(query: String): Flow<List<Uye>>

    @Query("SELECT * FROM uyeler WHERE uyerolu = :rol")
    fun rolEkle(rol: String) : Flow<List<Uye>>

    @Query("SELECT * FROM uyeler WHERE id = :mevcutUyeId LIMIT 1")
    suspend fun uyeGetirId(mevcutUyeId: Int): Uye?

}