package com.example.basicretrofitcoroutines.data.repository

import com.example.basicretrofitcoroutines.data.api.ApiHelper

class MainRepository(val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}