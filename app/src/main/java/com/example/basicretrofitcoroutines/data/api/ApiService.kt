package com.example.basicretrofitcoroutines.data.api

import com.example.basicretrofitcoroutines.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers() : List<User>
}