package com.example.rickandmorty.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Episode (
    @SerializedName("id")
    var id :Int,
    @SerializedName("name")
    var name : String,
    @SerializedName("air_date")
    var air_date :Int
)