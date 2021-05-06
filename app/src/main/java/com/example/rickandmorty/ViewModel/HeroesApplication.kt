package com.example.rickandmorty.ViewModel

import android.app.Application
import com.example.rickandmorty.room.DataBase

class HeroesApplication : Application(){
    val database by lazy { DataBase.getAppDataBase(this) }
    val repository by lazy { HeroesRepository(database.heroDao()) }
}