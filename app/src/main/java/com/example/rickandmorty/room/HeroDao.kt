package com.example.rickandmorty.room

import androidx.room.*
import com.example.rickandmorty.pojo.Heroes
import com.example.rickandmorty.pojo.HeroesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE

@Dao
interface HeroDao {
    @Query("SELECT * FROM hero")
    fun getAllHeroes(): Flow<List<HeroesResponse>>

    @Query("SELECT * FROM hero WHERE id = :id")
    fun getById(id : Int): HeroesResponse

    @Query("DELETE FROM hero")
    suspend fun deleteHeroes()

    @Insert
    suspend fun insertAllHeroes(heroesList: HeroesResponse)

    @Update
    suspend fun updateDataBase(heroesList: HeroesResponse)
}