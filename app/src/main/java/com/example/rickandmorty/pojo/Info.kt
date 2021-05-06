package com.example.rickandmorty.pojo

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
class Info (
    @SerializedName("pages")
    var pages: String,
    @SerializedName("next")
    var next: String,
    @SerializedName("prev")
    var prev: String
)