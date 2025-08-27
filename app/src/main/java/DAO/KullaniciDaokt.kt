package DAO

import Entity.Kullanici
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface KullaniciDao {

        @Query("SELECT * FROM kullanicilar ORDER BY ad")
        fun tumKullanicilar(): LiveData<List<Kullanici>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertkullanici(uye: Kullanici)

        @Delete
        suspend fun deleteKullanici(uye: Kullanici)

        @Update
        suspend fun updateKullanici(uye: Kullanici)

        @Query("SELECT * FROM kullanicilar WHERE kullaniciAdi = :kullaniciAdi AND sifre = :password")
        suspend fun kullaniciKontrol(kullaniciAdi: String, password: String): Kullanici?

        @Query("SELECT EXISTS(SELECT 1 FROM kullanicilar WHERE kullaniciAdi = :username)")
        suspend fun kullaniciVarMi(username: String): Boolean

        @Query("SELECT * FROM kullanicilar WHERE kullaniciAdi = :enteredUsername")
        suspend fun kullaniciByUsername(enteredUsername: String): Kullanici?

        @Query("SELECT * FROM kullanicilar WHERE id = :userId")
        suspend fun kullaniciById(userId: Int): Kullanici?

        @Update
        suspend fun sifreGuncelle(kullanici: Kullanici)

}















