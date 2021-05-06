package com.example.rickandmorty.ViewModel

import androidx.lifecycle.*
import com.example.rickandmorty.pojo.Heroes
import com.example.rickandmorty.pojo.HeroesResponse
import kotlinx.coroutines.launch

class HeroesViewModel(private val repository: HeroesRepository): ViewModel() {

    fun getAllHeroes(): LiveData<List<HeroesResponse>> {
        return repository.getAllHeroes().asLiveData()
    }
    fun getById(id: Int): HeroesResponse {
        return repository.getById(id)
    }
    fun insertAllHeroes(heroesList: HeroesResponse) = viewModelScope.launch {
            repository.insertHeroes(heroesList)
    }
    fun updateDataBase(heroesList: HeroesResponse) = viewModelScope.launch {
        repository.updateDataBase(heroesList)
    }
    fun deleteHero () = viewModelScope.launch {
        repository.deleteHero()
    }
}

class WordViewModelFactory(private val repository: HeroesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HeroesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}