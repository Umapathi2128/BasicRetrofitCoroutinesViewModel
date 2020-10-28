package com.example.basicretrofitcoroutines.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.basicretrofitcoroutines.data.api.ApiHelper
import com.example.basicretrofitcoroutines.data.repository.MainRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory(val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class")
    }
}