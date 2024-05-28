package com.example.gourmet_therecepieapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//builds connection to url and convert the incoming data  to data class compatible data
//builder used to
private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    val recipeService = retrofit.create(ApiService::class.java)


interface ApiService{
    //gets http request to categories.php
    @GET("categories.php")
    suspend fun getCategories():CategoryResponse

}