package com.example.rickandmorty.retrofit2

import com.example.rickandmorty.pojo.HeroesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url




interface RetrofitServices {
    @GET("character/")
    fun getHeroesList(@Query("page") page : Int): Call<HeroesResponse>
}