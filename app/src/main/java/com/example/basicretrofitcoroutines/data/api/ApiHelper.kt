package com.example.basicretrofitcoroutines.data.api

import retrofit2.Retrofit

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()
}