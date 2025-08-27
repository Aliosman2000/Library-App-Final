package Repository

import DAO.OkunanKitapDao
import Entity.OkunmusKitapEntity
import kotlinx.coroutines.flow.Flow

class OkunanKitapRepository(private val okunanKitapDao: OkunanKitapDao) {


    suspend fun insertOkunanKitap(okunanKitap: OkunmusKitapEntity) {
        okunanKitapDao.insert(okunanKitap)
    }


    fun getOkunanKitaplarByUserId(kullaniciId: String): Flow<List<OkunmusKitapEntity>> {
        // Parametre olarak gelen "kullaniciId" değerini DAO'ya iletiyoruz.
        return okunanKitapDao.Kullaniciidokunankitaplarigetir(kullaniciId)
    }


    suspend fun deleteOkunanKitapByBookId(kitapId: Int) {
        // DAO'daki fonksiyonun beklediği kitap ID'sini parametre olarak iletiyoruz.
        okunanKitapDao.idegoresilokunankitap(kitapId)
    }
}