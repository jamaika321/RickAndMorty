package com.example.rickandmorty.room

import android.content.Context
import androidx.room.*
import com.example.rickandmorty.pojo.HeroesResponse
import com.example.rickandmorty.retrofit2.Converters
import retrofit2.Converter

@Database(entities = [HeroesResponse::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DataBase: RoomDatabase() {
    abstract fun heroDao(): HeroDao

    companion object {
        @Volatile
        var INSTANCE: DataBase? = null

        fun getAppDataBase(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "word_database"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}