package com.example.gourmet_therecepieapp

import retrofit2.http.GET

interface ApiService{
    //gets http request to categories.php
    @GET("categories.php")
    suspend fun getCategories():CategoryResponse
}