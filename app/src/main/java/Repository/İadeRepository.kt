package Repository

import DAO.İadeDaoKt
import Entity.İade
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class İadeRepository(private val dao: İadeDaoKt) {

        val tumOduncler: LiveData<List<İade>> = dao.tümiadeler()
        suspend fun oduncEkle(iadee: İade) = dao.iadeEkle(iadee)
        suspend fun oduncSil(iadee: İade) = dao.iadeSil(iadee)
        suspend fun oduncGuncelle(iadee: İade) = dao.iadeGuncel(iadee)

    }
