package com.example.rickandmorty.ViewModel

import androidx.annotation.WorkerThread
import com.example.rickandmorty.pojo.Heroes
import com.example.rickandmorty.pojo.HeroesResponse
import com.example.rickandmorty.room.HeroDao
import kotlinx.coroutines.flow.Flow

class HeroesRepository(private val heroDao: HeroDao) {

    fun getAllHeroes(): Flow<List<HeroesResponse>> {
        return heroDao.getAllHeroes()
    }

    fun getById(id: Int): HeroesResponse {
        return heroDao.getById(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertHeroes(heroes: HeroesResponse){
        heroDao.insertAllHeroes(heroes)
    }
    suspend fun updateDataBase(heroes: HeroesResponse){
        heroDao.updateDataBase(heroes)
    }
    suspend fun deleteHero(){
        heroDao.deleteHeroes()
    }
}