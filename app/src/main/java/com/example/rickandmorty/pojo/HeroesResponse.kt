package com.example.rickandmorty.pojo

import androidx.annotation.NonNull
import androidx.room.*
import com.example.rickandmorty.retrofit2.Converters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "hero")
class HeroesResponse (
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        @TypeConverters(Converters::class)
        @SerializedName("results")
        var results: List<Heroes>,
        @TypeConverters(Converters::class)
        @SerializedName("info")
        var info: Info
)