package Database

import DAO.KitapDao
import DAO.KullaniciDao
import DAO.OkunanKitapDao
import DAO.UyeDao
import DAO.İadeDaoKt
import Entity.Kitap
import Entity.Kullanici
import Entity.OkunmusKitapEntity
import Entity.İade
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import Entity.Uye


@Database(entities = [Kullanici::class, Kitap::class, İade::class, OkunmusKitapEntity::class,Uye::class], version = 11, exportSchema = false)
abstract class KutuphaneDatabase : RoomDatabase() {


    abstract fun KitapDao(): KitapDao
    abstract fun kullaniciDao() : KullaniciDao
    abstract fun İadeDaoKt(): İadeDaoKt
    abstract fun okunanKitapDao(): OkunanKitapDao
    abstract fun UyeDao(): UyeDao


    companion object {
        @Volatile
        private var INSTANCE: KutuphaneDatabase? = null

        fun getDatabase(context: Context): KutuphaneDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KutuphaneDatabase::class.java,
                    "kutuphane_db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                val dao = getDatabase(context).KitapDao()
                                val kullaniciDao = getDatabase(context).kullaniciDao()
                                val iadedao = getDatabase(context).İadeDaoKt()
                                val uyeDao = getDatabase(context).UyeDao()




                                dao.kitapEkle(Kitap(kitapadi = "Suç ve Ceza", yazar = "Fyodor Dostoyevski", category = "Roman", stokadedi = 5, isbn = "978-6051110531"))
                                dao.kitapEkle(Kitap(kitapadi = "Kürk Mantolu Madonna", yazar = "Sabahattin Ali", category = "Roman", stokadedi = 3, isbn = "978-9750836257"))
                                dao.kitapEkle(Kitap(kitapadi = "Sefiller", yazar = "Victor Hugo", category = "Klasik", stokadedi = 4, isbn = "978-9754702382"))
                                dao.kitapEkle(Kitap(kitapadi = "Simyacı", yazar = "Paulo Coelho", category = "Felsefe", stokadedi = 6, isbn = "978-6053609900"))
                                dao.kitapEkle(Kitap(kitapadi = "Sefiller", yazar = "Victor Hugo", category = "Klasik", stokadedi = 4, isbn = "978-9754702382"))
                                dao.kitapEkle(Kitap(kitapadi = "Simyacı", yazar = "Paulo Coelho", category = "Felsefe", stokadedi = 6, isbn = "978-6053609900"))
                                dao.kitapEkle(Kitap(kitapadi = "Kırmızı Pazartesi", yazar = "Gabriel Garcia Marquez", category = "Öykü", stokadedi = 2, isbn = "978-9755100524"))
                                dao.kitapEkle(Kitap(kitapadi = "1984", yazar = "George Orwell", category = "Bilim Kurgu", stokadedi = 3, isbn = "978-9750730302"))
                                dao.kitapEkle(Kitap(kitapadi = "Sefiller", yazar = "Victor Hugo", category = "Roman", stokadedi = 8, isbn = "978-9754060851"))
                                dao.kitapEkle(Kitap(kitapadi = "Yüz Yıllık Yalnızlık", yazar = "Gabriel Garcia Marquez", category = "Büyülü Gerçekçilik", stokadedi = 2, isbn = "978-9750711116"))
                                dao.kitapEkle(Kitap(kitapadi = "Küçük Prens", yazar = "Antoine de Saint-Exupéry", category = "Çocuk Kitabı", stokadedi = 10, isbn = "978-9750731699"))
                                dao.kitapEkle(Kitap(kitapadi = "Simyacı", yazar = "Paulo Coelho", category = "Felsefi Roman", stokadedi = 7, isbn = "978-9750702008"))
                                dao.kitapEkle(Kitap(kitapadi = "Dava", yazar = "Franz Kafka", category = "Roman", stokadedi = 4, isbn = "978-9750720495"))
                                dao.kitapEkle(Kitap(kitapadi = "Anna Karenina", yazar = "Lev Tolstoy", category = "Roman", stokadedi = 6, isbn = "978-9750704040"))
                                dao.kitapEkle(Kitap(kitapadi = "Cesur Yeni Dünya", yazar = "Aldous Huxley", category = "Distopya", stokadedi = 3, isbn = "978-9750714101"))
                                dao.kitapEkle(Kitap(kitapadi = "Uçurtma Avcısı", yazar = "Khaled Hosseini", category = "Dram", stokadedi = 9, isbn = "978-9756475471"))
                                dao.kitapEkle(Kitap(kitapadi = "Dönüşüm", yazar = "Franz Kafka", category = "Roman", stokadedi = 5, isbn = "978-9750707768"))
                                dao.kitapEkle(Kitap(kitapadi = "Satranç", yazar = "Stefan Zweig", category = "Psikolojik Roman", stokadedi = 6, isbn = "978-9750728774"))
                                dao.kitapEkle(Kitap(kitapadi = "Yabancı", yazar = "Albert Camus", category = "Varoluşçuluk", stokadedi = 4, isbn = "978-9750730418"))
                                dao.kitapEkle(Kitap(kitapadi = "Don Kişot", yazar = "Miguel de Cervantes", category = "Komedi", stokadedi = 2, isbn = "978-6050907005"))
                                dao.kitapEkle(Kitap(kitapadi = "Hobbit", yazar = "J.R.R. Tolkien", category = "Fantastik", stokadedi = 8, isbn = "978-9752733959"))
                                dao.kitapEkle(Kitap(kitapadi = "Kürk Mantolu Madonna", yazar = "Sabahattin Ali", category = "Roman", stokadedi = 10, isbn = "978-9758540843"))
                                dao.kitapEkle(Kitap(kitapadi = "Şeker Portakalı", yazar = "José Mauro de Vasconcelos", category = "Çocuk Kitabı", stokadedi = 7, isbn = "978-9750734005"))
                                dao.kitapEkle(Kitap(kitapadi = "Sineklerin Tanrısı", yazar = "William Golding", category = "Klasik", stokadedi = 4, isbn = "978-9755106179"))
                                dao.kitapEkle(Kitap(kitapadi = "Yeraltından Notlar", yazar = "Fyodor Dostoyevski", category = "Roman", stokadedi = 6, isbn = "978-9750701019"))
                                dao.kitapEkle(Kitap(kitapadi = "Kırmızı ve Siyah", yazar = "Stendhal", category = "Roman", stokadedi = 5, isbn = "978-9750712007",))
                                dao.kitapEkle(Kitap(kitapadi = "Suç ve Ceza", yazar = "Fyodor Dostoyevski", category = "Roman", stokadedi = 5, isbn = "978-6051110531"))
                                dao.kitapEkle(Kitap(kitapadi = "Hacı Murat", yazar = "Lev Tolstoy", category = "Klasik", stokadedi = 5, isbn = "43559490940-434"))
                                dao.kitapEkle(Kitap(kitapadi = "Ölü Canlar", yazar = "Nikolay Gogol", category = "Klasik Roman", stokadedi = 5, isbn = "235454545"))
                                dao.kitapEkle(Kitap(kitapadi = "Osmanlı Sultanları", yazar = "Sevinç Kuşoğlu", category = "Tarih", stokadedi = 5, isbn = "32432554-232"))
                                dao.kitapEkle(Kitap(kitapadi = "Ölümle Randevu", yazar = "Agatha Christie", category = "Gizem", stokadedi = 5, isbn = "234-32423456534"))
                                dao.kitapEkle(Kitap(kitapadi = "İntibah", yazar = "Namık Kemal", category = "Roman", stokadedi = 5, isbn = "454354345435-32434"))
                                dao.kitapEkle(Kitap(kitapadi = "Beyaz Gemi", yazar = "Cengiz Aytmatov", category = "Roman", stokadedi = 3, isbn = "978-975-406-056-1"))
                                dao.kitapEkle(Kitap(kitapadi = "İnce Memed", yazar = "Yaşar Kemal", category = "Roman", stokadedi = 4, isbn = "978-975-07-2615-2"))
                                dao.kitapEkle(Kitap(kitapadi = "Semerkant", yazar = "Amin Maalouf", category = "Tarihi Kurgu", stokadedi = 5, isbn = "978-975-07-1603-6"))
                                dao.kitapEkle(Kitap(kitapadi = "Puslu Kıtalar Atlası", yazar = "İhsan Oktay Anar", category = "Fantastik Roman", stokadedi = 3, isbn = "978-975-470-496-6"))
                                dao.kitapEkle(Kitap(kitapadi = "Aylak Adam", yazar = "Yusuf Atılgan", category = "Modern Klasik", stokadedi = 2, isbn = "978-975-470-089-0"))
                                dao.kitapEkle(Kitap(kitapadi = "Tutunamayanlar", yazar = "Oğuz Atay", category = "Modern Klasik", stokadedi = 5, isbn = "978-975-470-297-9"))
                                dao.kitapEkle(Kitap(kitapadi = "Bir Delinin Hatıra Defteri", yazar = "Nikolay Gogol", category = "Öykü", stokadedi = 4, isbn = "978-605-360-316-8"))
                                dao.kitapEkle(Kitap(kitapadi = "Gülün Adı", yazar = "Umberto Eco", category = "Tarihi Roman", stokadedi = 6, isbn = "978-975-07-0749-2"))
                                dao.kitapEkle(Kitap(kitapadi = "Anna Karenina", yazar = "Lev Tolstoy", category = "Klasik", stokadedi = 7, isbn = "978-975-07-0404-0"))
                                dao.kitapEkle(Kitap(kitapadi = "Gazap Üzümleri", yazar = "John Steinbeck", category = "Klasik Roman", stokadedi = 4, isbn = "978-975-07-0130-9"))
                                dao.kitapEkle(Kitap(kitapadi = "İnsan Ne İle Yaşar", yazar = "Lev Tolstoy", category = "Felsefe", stokadedi = 5, isbn = "978-605-186-064-0"))
                                dao.kitapEkle(Kitap(kitapadi = "Sineklerin Tanrısı", yazar = "William Golding", category = "Distopya", stokadedi = 3, isbn = "978-975-510-617-9"))
                                dao.kitapEkle(Kitap(kitapadi = "Karamazov Kardeşler", yazar = "Fyodor Dostoyevski", category = "Klasik Roman", stokadedi = 6, isbn = "978-975-07-0428-6"))
                                dao.kitapEkle(Kitap(kitapadi = "Hayvan Çiftliği", yazar = "George Orwell", category = "Distopya", stokadedi = 8, isbn = "978-975-07-1601-2"))
                                dao.kitapEkle(Kitap(kitapadi = "Bilinmeyen Bir Kadının Mektubu", yazar = "Stefan Zweig", category = "Psikolojik Roman", stokadedi = 5, isbn = "978-975-07-1681-4"))
                                dao.kitapEkle(Kitap(kitapadi = "Kırmızı Pazartesi", yazar = "Gabriel Garcia Marquez", category = "Öykü", stokadedi = 4, isbn = "978-975-510-052-4"))
                                dao.kitapEkle(Kitap(kitapadi = "Uçurtma Avcısı", yazar = "Khaled Hosseini", category = "Dram", stokadedi = 7, isbn = "978-975-6475-47-1"))
                                dao.kitapEkle(Kitap(kitapadi = "Harry Potter ve Felsefe Taşı", yazar = "J.K. Rowling", category = "Fantastik", stokadedi = 10, isbn = "978-975-08-0294-0"))
                                dao.kitapEkle(Kitap(kitapadi = "Şeker Portakalı", yazar = "José Mauro de Vasconcelos", category = "Çocuk Edebiyatı", stokadedi = 6, isbn = "978-975-07-3400-5"))
                                dao.kitapEkle(Kitap(kitapadi = "Martin Eden", yazar = "Jack London", category = "Klasik", stokadedi = 5, isbn = "978-975-07-3211-1"))
                                dao.kitapEkle(Kitap(kitapadi = "Oblomov", yazar = "İvan Gonçarov", category = "Roman", stokadedi = 4, isbn = "978-975-07-2858-3"))
                                dao.kitapEkle(Kitap(kitapadi = "Yabancı", yazar = "Albert Camus", category = "Varoluşçuluk", stokadedi = 3, isbn = "978-975-07-3041-8"))
                                dao.kitapEkle(Kitap(kitapadi = "Yüzyıllık Yalnızlık", yazar = "Gabriel Garcia Marquez", category = "Büyülü Gerçekçilik", stokadedi = 5, isbn = "978-975-07-1111-6"))
                                dao.kitapEkle(Kitap(kitapadi = "Dönüşüm", yazar = "Franz Kafka", category = "Roman", stokadedi = 4, isbn = "978-975-07-0776-8"))
                                dao.kitapEkle(Kitap(kitapadi = "Fareler ve İnsanlar", yazar = "John Steinbeck", category = "Roman", stokadedi = 6, isbn = "978-975-07-0203-9"))
                                dao.kitapEkle(Kitap(kitapadi = "Vadideki Zambak", yazar = "Honoré de Balzac", category = "Klasik", stokadedi = 3, isbn = "978-975-07-0599-2"))
                                dao.kitapEkle(Kitap(kitapadi = "Körleşme", yazar = "Elias Canetti", category = "Roman", stokadedi = 2, isbn = "978-975-07-3868-3"))
                                dao.kitapEkle(Kitap(kitapadi = "Fahrenheit 451", yazar = "Ray Bradbury", category = "Bilim Kurgu", stokadedi = 4, isbn = "978-975-07-1123-9"))
                                dao.kitapEkle(Kitap(kitapadi = "Cesur Yeni Dünya", yazar = "Aldous Huxley", category = "Distopya", stokadedi = 5, isbn = "978-975-07-1410-1"))
                                dao.kitapEkle(Kitap(kitapadi = "Goriot Baba", yazar = "Honoré de Balzac", category = "Klasik", stokadedi = 3, isbn = "978-975-07-0131-6"))





                                kullaniciDao.insertkullanici(Kullanici(ad = "Ali", soyad = "Yılmaz", eposta = "ali.yilmaz@example.com", kullaniciAdi = "aliyilmaz", sifre = "12345", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Ayşe", soyad = "Demir", eposta = "ayse.demir@example.com", kullaniciAdi = "aysedemir", sifre = "5678", rol = "admin"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Mehmet", soyad = "Kin", eposta = "mehmet.kaya@example.com", kullaniciAdi = "mehmetk", sifre = "9101112", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Zeynep", soyad = "Çelik", eposta = "zeynep.celik@example.com", kullaniciAdi = "zeynepc", sifre = "abcde", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Emre", soyad = "Taş", eposta = "emre.tas@example.com", kullaniciAdi = "emretas", sifre = "fghij", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Selin", soyad = "Kurt", eposta = "selin.kurt@example.com", kullaniciAdi = "selink", sifre = "klmno", rol = "admin"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Burak", soyad = "Öztürk", eposta = "burak.ozturk@example.com", kullaniciAdi = "burako", sifre = "pqrst", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Ceren", soyad = "Aydın", eposta = "ceren.aydin@example.com", kullaniciAdi = "cerena", sifre = "uvwxy", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Deniz", soyad = "Aksoy", eposta = "deniz.aksoy@example.com", kullaniciAdi = "deniza", sifre = "zyxw", rol = "admin"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Ece", soyad = "Turan", eposta = "ece.turan@example.com", kullaniciAdi = "ecet", sifre = "vuts", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Fatih", soyad = "Korkmaz", eposta = "fatih.korkmaz@example.com", kullaniciAdi = "fatihk", sifre = "rqpo", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Gizem", soyad = "Şahin", eposta = "gizem.sahin@example.com", kullaniciAdi = "gizems", sifre = "nmlk", rol = "admin"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Hakan", soyad = "Yıldız", eposta = "hakan.yildiz@example.com", kullaniciAdi = "hakany", sifre = "jihg", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "İrem", soyad = "Güneş", eposta = "irem.gunes@example.com", kullaniciAdi = "iremg", sifre = "fedc", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Can", soyad = "Duman", eposta = "can.duman@example.com", kullaniciAdi = "cand", sifre = "ba98", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Aslı", soyad = "Sarı", eposta = "asli.sari@example.com", kullaniciAdi = "aslis", sifre = "7654", rol = "admin"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Kaan", soyad = "Kara", eposta = "kaan.kara@example.com", kullaniciAdi = "kaank", sifre = "3210", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Melis", soyad = "Özdemir", eposta = "melis.ozdemir@example.com", kullaniciAdi = "meliso", sifre = "1357", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Ozan", soyad = "Efe", eposta = "ozan.efe@example.com", kullaniciAdi = "ozane", sifre = "2468", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Pınar", soyad = "Şen", eposta = "pinar.sen@example.com", kullaniciAdi = "pinars", sifre = "1987", rol = "admin"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Ayşe", soyad = "Kaya", eposta = "ayse.kaya@example.com", kullaniciAdi = "ayse_k", sifre = "ayse123", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Fatma", soyad = "Aslan", eposta = "fatma.aslan@example.com", kullaniciAdi = "fatma.a", sifre = "fatma123", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Can", soyad = "Kartal", eposta = "can.kartal@example.com", kullaniciAdi = "can_k", sifre = "can_kr", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Elif", soyad = "Şahin", eposta = "elif.sahin@example.com", kullaniciAdi = "elif_s", sifre = "elif123", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Hasan", soyad = "Yıldız", eposta = "hasan.yildiz@example.com", kullaniciAdi = "hasan.y", sifre = "hasan456", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Zeynep", soyad = "Çelik", eposta = "zeynep.celik@example.com", kullaniciAdi = "zeynep_c", sifre = "zeynepc", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Merve", soyad = "Güneş", eposta = "merve.gunes@example.com", kullaniciAdi = "merve.g", sifre = "mrvgnş", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Furkan", soyad = "Doğan", eposta = "furkan.dogan@example.com", kullaniciAdi = "furkand", sifre = "frkndgn", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Ebru", soyad = "Arslan", eposta = "ebru.arslan@example.com", kullaniciAdi = "ebru.a", sifre = "ebrars", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Gökhan", soyad = "Demir", eposta = "gokhan.demir@example.com", kullaniciAdi = "gokhan.d", sifre = "gkhndmr", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Seda", soyad = "Koç", eposta = "seda.koc@example.com", kullaniciAdi = "seda.k", sifre = "sdakoc", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Mert", soyad = "Polat", eposta = "mert.polat@example.com", kullaniciAdi = "mert.p", sifre = "mertpol", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Kübra", soyad = "Akın", eposta = "kubra.akin@example.com", kullaniciAdi = "kubra.a", sifre = "kbrkn", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Okan", soyad = "Korkmaz", eposta = "okan.korkmaz@example.com", kullaniciAdi = "okan_k", sifre = "oknkrmz", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Gizem", soyad = "Sayar", eposta = "gizem.sayar@example.com", kullaniciAdi = "gizem.s", sifre = "gzmsyr", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Tuna", soyad = "Yıldırım", eposta = "tuna.yildirim@example.com", kullaniciAdi = "tuna.y", sifre = "tnylrm", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Pelin", soyad = "Toprak", eposta = "pelin.toprak@example.com", kullaniciAdi = "pelin_t", sifre = "plntprk", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Emir", soyad = "Kurt", eposta = "emir.kurt@example.com", kullaniciAdi = "emir.k", sifre = "emrkrt", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Ceyda", soyad = "Işık", eposta = "ceyda.isik@example.com", kullaniciAdi = "ceyda.i", sifre = "cydsk", rol = "uye"))
                                kullaniciDao.insertkullanici(Kullanici(ad = "Barış", soyad = "Özdemir", eposta = "baris.ozdemir@example.com", kullaniciAdi = "baris.o", sifre = "brsozd", rol = "uye"))



                                iadedao.iadeEkle(İade(uyeid = 1, kitapId = 1, odunc_tarihi = 1691001600000, iade_tarihi = null, iadekontrol = "loaned"))
                                iadedao.iadeEkle(İade(uyeid = 2, kitapId = 3, odunc_tarihi = 1691001605000, iade_tarihi = null, iadekontrol = "loaned"))
                                iadedao.iadeEkle(İade(uyeid = 3, kitapId = 5, odunc_tarihi = 1691001610000, iade_tarihi = null, iadekontrol = "loaned"))
                                iadedao.iadeEkle(İade(uyeid = 4, kitapId = 7, odunc_tarihi = 1691001615000, iade_tarihi = null, iadekontrol = "loaned"))
                                iadedao.iadeEkle(İade(uyeid = 5, kitapId = 9, odunc_tarihi = 1691001620000, iade_tarihi = null, iadekontrol = "loaned"))
                                iadedao.iadeEkle(İade(uyeid = 1, kitapId = 2, odunc_tarihi = 1691001625000, iade_tarihi = 1691001630000, iadekontrol = "returned"))
                                iadedao.iadeEkle(İade(uyeid = 2, kitapId = 4, odunc_tarihi = 1691001635000, iade_tarihi = 1691001640000, iadekontrol = "returned"))
                                iadedao.iadeEkle(İade(uyeid = 3, kitapId = 6, odunc_tarihi = 1691001645000, iade_tarihi = 1691001650000, iadekontrol = "returned"))
                                iadedao.iadeEkle(İade(uyeid = 4, kitapId = 8, odunc_tarihi = 1691001655000, iade_tarihi = 1691001660000, iadekontrol = "returned"))
                                iadedao.iadeEkle(İade(uyeid = 5, kitapId = 10, odunc_tarihi = 1691001665000, iade_tarihi = 1691001670000, iadekontrol = "returned"))
                                iadedao.iadeEkle(İade(uyeid = 6, kitapId = 11, odunc_tarihi = 1691001675000, iade_tarihi = null, iadekontrol = "loaned"))
                                iadedao.iadeEkle(İade(uyeid = 7, kitapId = 13, odunc_tarihi = 1691001680000, iade_tarihi = null, iadekontrol = "loaned"))
                                iadedao.iadeEkle(İade(uyeid = 8, kitapId = 15, odunc_tarihi = 1691001685000, iade_tarihi = null, iadekontrol = "loaned"))
                                iadedao.iadeEkle(İade(uyeid = 9, kitapId = 17, odunc_tarihi = 1691001690000, iade_tarihi = null, iadekontrol = "loaned"))
                                iadedao.iadeEkle(İade(uyeid = 10, kitapId = 19,odunc_tarihi = 1691001695000, iade_tarihi = null, iadekontrol = "loaned"))
                                iadedao.iadeEkle(İade(uyeid = 11, kitapId = 20,odunc_tarihi = 1691001700000, iade_tarihi = 1691001705000, iadekontrol = "returned"))
                                iadedao.iadeEkle(İade(uyeid = 12, kitapId = 18,odunc_tarihi = 1691001710000, iade_tarihi = null, iadekontrol = "loaned"))
                                iadedao.iadeEkle(İade(uyeid = 13, kitapId = 16,odunc_tarihi = 1691001715000, iade_tarihi = 1691001720000, iadekontrol = "returned"))
                                iadedao.iadeEkle(İade(uyeid = 14, kitapId = 14,odunc_tarihi = 1691001725000, iade_tarihi = 1691001730000, iadekontrol = "returned"))
                                iadedao.iadeEkle(İade(uyeid = 15, kitapId = 12,odunc_tarihi = 1691001735000, iade_tarihi = null, iadekontrol = "loaned"))



                                uyeDao.uyeEkle(Uye(uyeadi = "Ali", uyesoyadi = "Yılmaz", eposta = "ali.yilmaz@example.com", sifre = "12345", uyeno = "1", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Ayşe", uyesoyadi = "Demir", eposta = "ayse.demir@example.com", sifre = "5678", uyeno = "2", uyerolu = "admin"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Mehmet", uyesoyadi = "Kin", eposta = "mehmet.kaya@example.com", sifre = "9101112", uyeno = "3", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Zeynep", uyesoyadi = "Çelik", eposta = "zeynep.celik@example.com", sifre = "abcde", uyeno = "4", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Emre", uyesoyadi = "Taş", eposta = "emre.tas@example.com", sifre = "fghij", uyeno = "5", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Selin", uyesoyadi = "Kurt", eposta = "selin.kurt@example.com", sifre = "klmno", uyeno = "6", uyerolu = "admin"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Burak", uyesoyadi = "Öztürk", eposta = "burak.ozturk@example.com", sifre = "pqrst", uyeno = "7", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Ceren", uyesoyadi = "Aydın", eposta = "ceren.aydin@example.com", sifre = "uvwxy", uyeno = "8", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Deniz", uyesoyadi = "Aksoy", eposta = "deniz.aksoy@example.com", sifre = "zyxw", uyeno = "9", uyerolu = "admin"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Ece", uyesoyadi = "Turan", eposta = "ece.turan@example.com", sifre = "vuts", uyeno = "10", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Fatih", uyesoyadi = "Korkmaz", eposta = "fatih.korkmaz@example.com", sifre = "rqpo", uyeno = "11", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Gizem", uyesoyadi = "Şahin", eposta = "gizem.sahin@example.com", sifre = "nmlk", uyeno = "12", uyerolu = "admin"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Hakan", uyesoyadi = "Yıldız", eposta = "hakan.yildiz@example.com", sifre = "jihg", uyeno = "13", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "İrem", uyesoyadi = "Güneş", eposta = "irem.gunes@example.com", sifre = "fedc", uyeno = "14", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Can", uyesoyadi = "Duman", eposta = "can.duman@example.com", sifre = "ba98", uyeno = "15", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Aslı", uyesoyadi = "Sarı", eposta = "asli.sari@example.com", sifre = "7654", uyeno = "16", uyerolu = "admin"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Kaan", uyesoyadi = "Kara", eposta = "kaan.kara@example.com", sifre = "3210", uyeno = "17", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Melis", uyesoyadi = "Özdemir", eposta = "melis.ozdemir@example.com", sifre = "1357", uyeno = "18", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Ozan", uyesoyadi = "Efe", eposta = "ozan.efe@example.com", sifre = "2468", uyeno = "19", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Pınar", uyesoyadi = "Şen", eposta = "pinar.sen@example.com", sifre = "1987", uyeno = "20", uyerolu = "admin"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Ayşe", uyesoyadi = "Kaya", eposta = "ayse.kaya@example.com", sifre = "ayse123", uyeno = "21", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Fatma", uyesoyadi = "Aslan", eposta = "fatma.aslan@example.com", sifre = "fatma123", uyeno = "22", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Can", uyesoyadi = "Kartal", eposta = "can.kartal@example.com", sifre = "can_kr", uyeno = "23", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Elif", uyesoyadi = "Şahin", eposta = "elif.sahin@example.com", sifre = "elif123", uyeno = "24", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Hasan", uyesoyadi = "Yıldız", eposta = "hasan.yildiz@example.com", sifre = "hasan456", uyeno = "25", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Zeynep", uyesoyadi = "Çelik", eposta = "zeynep.celik@example.com", sifre = "zeynepc", uyeno = "26", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Merve", uyesoyadi = "Güneş", eposta = "merve.gunes@example.com", sifre = "mrvgnş", uyeno = "27", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Furkan", uyesoyadi = "Doğan", eposta = "furkan.dogan@example.com", sifre = "frkndgn", uyeno = "28", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Ebru", uyesoyadi = "Arslan", eposta = "ebru.arslan@example.com", sifre = "ebrars", uyeno = "29", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Gökhan", uyesoyadi = "Demir", eposta = "gokhan.demir@example.com", sifre = "gkhndmr", uyeno = "30", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Seda", uyesoyadi = "Koç", eposta = "seda.koc@example.com", sifre = "sdakoc", uyeno = "31", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Mert", uyesoyadi = "Polat", eposta = "mert.polat@example.com", sifre = "mertpol", uyeno = "32", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Kübra", uyesoyadi = "Akın", eposta = "kubra.akin@example.com", sifre = "kbrkn", uyeno = "33", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Okan", uyesoyadi = "Korkmaz", eposta = "okan.korkmaz@example.com", sifre = "oknkrmz", uyeno = "34", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Gizem", uyesoyadi = "Sayar", eposta = "gizem.sayar@example.com", sifre = "gzmsyr", uyeno = "35", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Tuna", uyesoyadi = "Yıldırım", eposta = "tuna.yildirim@example.com", sifre = "tnylrm", uyeno = "36", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Pelin", uyesoyadi = "Toprak", eposta = "pelin.toprak@example.com", sifre = "plntprk", uyeno = "37", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Emir", uyesoyadi = "Kurt", eposta = "emir.kurt@example.com", sifre = "emrkrt", uyeno = "38", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Ceyda", uyesoyadi = "Işık", eposta = "ceyda.isik@example.com", sifre = "cydsk", uyeno = "39", uyerolu = "uye"))
                                uyeDao.uyeEkle(Uye(uyeadi = "Barış", uyesoyadi = "Özdemir", eposta = "baris.ozdemir@example.com", sifre = "brsozd", uyeno = "40", uyerolu = "uye"))

                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}