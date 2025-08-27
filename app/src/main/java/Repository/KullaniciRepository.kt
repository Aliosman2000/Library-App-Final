package Repository

import DAO.KullaniciDao
import Entity.Kullanici
import androidx.lifecycle.LiveData


class KullaniciRepository(private val dao: KullaniciDao) {

    // LiveData'yı doğrudan DAO'dan alıyoruz
    val tumKullanicilar: LiveData<List<Kullanici>> = dao.tumKullanicilar()

    suspend fun uyeEkle(uye: Kullanici) = dao.insertkullanici(uye)
    suspend fun uyeSil(uye: Kullanici) = dao.deleteKullanici(uye)
    suspend fun uyeGuncelle(uye: Kullanici) = dao.updateKullanici(uye)


    suspend fun kullaniciId(userId: Int) = dao.kullaniciById(userId)





    suspend fun kullaniciKontrol(kullaniciAdi: String, password: String): Kullanici? {
        return dao.kullaniciKontrol(kullaniciAdi, password)
    }


}
