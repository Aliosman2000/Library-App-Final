package ViewModel

import Entity.İade
import Repository.İadeRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class İadeViewModel(private val repository: İadeRepository) : ViewModel() {

    val tumUyeler: LiveData<List<İade>> = repository.tumOduncler


    fun oduncEkle(iade: İade) = viewModelScope.launch {
        repository.oduncEkle(iade)
    }

    fun oduncSil(iade: İade) = viewModelScope.launch {
        repository.oduncSil(iade)
    }

    fun oduncGuncelle(iade: İade) = viewModelScope.launch {
        repository.oduncGuncelle(iade)
    }
}

class İadeFactory(private val repo: İadeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(İadeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return İadeViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
