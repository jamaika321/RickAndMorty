package com.example.rickandmorty.retrofit2

object Common {
    private val BASE_URL = "https://rickandmortyapi.com/api/"
    val retrofitServices: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}