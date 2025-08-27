package ViewModel


import Entity.Kullanici
import Repository.KullaniciRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData

class KullaniciViewModel(private val repository: KullaniciRepository) : ViewModel() {

    // Repository'den LiveData'yı doğrudan alıyoruz
    val tumUyeler: LiveData<List<Kullanici>> = repository.tumKullanicilar

    fun uyeEkle(uye: Kullanici) = viewModelScope.launch {
        repository.uyeEkle(uye)
    }

    fun uyeGuncelle(uye: Kullanici) = viewModelScope.launch {
        repository.uyeGuncelle(uye)
    }

    fun uyeSil(uye: Kullanici) = viewModelScope.launch {
        repository.uyeSil(uye)
    }

    fun sifreSifirla(kullaniciAdi: String, eposta: String, onSuccess: (yeniSifre: String) -> Unit, onError: (String) -> Unit)
    {
        viewModelScope.launch {
            val kullanici = repository.kullaniciKontrol(kullaniciAdi, eposta)
            if (kullanici != null) {
                val yeniSifre = generateRandomPassword()
                val guncellenmisKullanici = kullanici.copy(sifre = yeniSifre)
                repository.uyeGuncelle(guncellenmisKullanici)
                onSuccess(yeniSifre)
            } else {
                onError("Kullanıcı adı veya e-posta hatalı!")
            }
        }
    }

    private fun generateRandomPassword(): String {
        val chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        return (1..8).map { chars.random() }.joinToString("")
    }
}




class KullaniciFactory(private val repo: KullaniciRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(KullaniciViewModel::class.java) -> {
                KullaniciViewModel(repo) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}