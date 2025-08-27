package ViewModel

import Entity.OkunmusKitapEntity
import Repository.OkunanKitapRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class OkunanKitapViewModel(private val repository: OkunanKitapRepository) : ViewModel() {

    // UI'ın gözlemleyebileceği okunan kitaplar listesi.
    private val _okunanKitaplar = MutableLiveData<List<OkunmusKitapEntity>>()
    val okunanKitaplar: LiveData<List<OkunmusKitapEntity>> = _okunanKitaplar


    fun insertOkunanKitap(okunanKitap: OkunmusKitapEntity) {
        viewModelScope.launch {
            repository.insertOkunanKitap(okunanKitap)
        }
    }

    fun getOkunanKitaplar(kullaniciId: String) {
        viewModelScope.launch {
            // Repository'den gelen, kullanıcı ID'sine göre filtrelenmiş veriyi dinliyoruz.
            repository.getOkunanKitaplarByUserId(kullaniciId).collectLatest { kitapListesi ->
                _okunanKitaplar.value = kitapListesi
            }
        }
    }


    fun deleteOkunanKitapByBookId(kitapId: Int) {
        viewModelScope.launch {
            repository.deleteOkunanKitapByBookId(kitapId)
        }
    }
}

class OkunanKitapViewModelFactory(private val repository: OkunanKitapRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OkunanKitapViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OkunanKitapViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}