package DAO

import Entity.Kitap
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface KitapDao {

    @Query("SELECT * FROM kitaplar ORDER BY kitapadi ASC")
    fun tumKitaplariGetir(): Flow<List<Kitap>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun kitapEkle(yeniKitap: Kitap): Long?

    @Delete
    suspend fun kitapSil(kitap: Kitap)

    @Update
    suspend fun kitapGuncelle(kitap: Kitap)

    // Kitap adına göre arama
    @Query("SELECT * FROM kitaplar WHERE kitapadi LIKE '%' || :query || '%' COLLATE NOCASE")
    fun kitapAdinaGoreAra(query: String): Flow<List<Kitap>>

    // Yazar adına göre arama
    @Query("SELECT * FROM kitaplar WHERE yazar LIKE '%' || :query || '%' COLLATE NOCASE")
    fun yazarAdinaGoreAra(query: String): Flow<List<Kitap>>

    // Kategoriye göre arama
    @Query("SELECT * FROM kitaplar WHERE category LIKE '%' || :query || '%' COLLATE NOCASE")
    fun kategoriyeGoreAra(query: String): Flow<List<Kitap>>


    @Query("SELECT * FROM kitaplar WHERE kitapadi LIKE '%' || :query || '%' OR yazar LIKE '%' || :query || '%' OR category LIKE '%' || :query || '%' COLLATE NOCASE")
    fun aramaYap(query: String): Flow<List<Kitap>>

    @Query("SELECT * FROM kitaplar WHERE id = :kitapId LIMIT 1")
    suspend fun kitapGetirId(kitapId: Int): Kitap?

    @Query("SELECT * FROM kitaplar WHERE isbn = :isbn")
    fun kitapgetirIsbn(isbn: String): Kitap?

}

