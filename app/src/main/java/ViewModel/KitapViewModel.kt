package ViewModel


import Entity.Kitap
import Repository.KitapRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow


class KitapViewModel(private val repository: KitapRepository) : ViewModel() {

    val tumKitaplar: LiveData<List<Kitap>> = repository.tumKitaplar.asLiveData()

    fun kitapEkle(kitap: Kitap) = viewModelScope.launch {
        repository.kitapEkle(kitap)
    }

    fun kitapSil(kitap: Kitap) = viewModelScope.launch {
        repository.kitapSil(kitap)
    }

    fun kitapGuncelle(kitap: Kitap) = viewModelScope.launch {
        repository.kitapGuncelle(kitap)
    }

    fun kitapAra(query: String, filtre: String): LiveData<List<Kitap>> {
        return repository.kitapAra(query.trim(), filtre).asLiveData()
    }
}







class KitapViewModelFactory(private val repo: KitapRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KitapViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KitapViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}













