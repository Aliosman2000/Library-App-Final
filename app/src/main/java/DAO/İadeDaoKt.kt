package DAO

import Entity.Kitap
import Entity.İade
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface İadeDaoKt {

    @Query("SELECT * FROM İade ORDER BY iade_tarihi DESC")
    fun tümiadeler(): LiveData<List<İade>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun iadeEkle(iade: İade)

    @Delete
    suspend fun iadeSil(iade: İade)

    @Update
    suspend fun iadeGuncel(iade: İade)
}







