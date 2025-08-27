package Repository

import DAO.KitapDao
import Entity.Kitap
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import java.util.Locale


class KitapRepository(private val dao: KitapDao) {

    val tumKitaplar: Flow<List<Kitap>> = dao.tumKitaplariGetir()

    suspend fun kitapEkle(kitap: Kitap) = dao.kitapEkle(kitap)
    suspend fun kitapSil(kitap: Kitap) = dao.kitapSil(kitap)
    suspend fun kitapGuncelle(kitap: Kitap) = dao.kitapGuncelle(kitap)

    fun kitapAra(query: String, filtre: String): Flow<List<Kitap>> {
        val normalized = query.lowercase(Locale("tr", "TR"))

        return when (filtre) {
            "yazar" -> dao.yazarAdinaGoreAra(normalized)
            "kategori" -> dao.kategoriyeGoreAra(normalized)
            else -> dao.kitapAdinaGoreAra(normalized) // default kitap adı
        }
    }
}