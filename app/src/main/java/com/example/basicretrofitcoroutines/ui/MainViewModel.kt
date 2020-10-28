package com.example.basicretrofitcoroutines.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.basicretrofitcoroutines.data.repository.MainRepository
import com.example.basicretrofitcoroutines.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(val mainRepository: MainRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        }catch (e : Exception){
            emit(Resource.error(data = null,message = e.message ?:"Exception occured"))
        }
    }
}