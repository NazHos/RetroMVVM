package com.tae.apiimplementation

import android.util.Config.DEBUG
import android.util.Log
import android.util.Log.DEBUG
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel (private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val peopleList = MutableLiveData<List<PeopleModelItem>>()
    val loading = MutableLiveData<Boolean>()
    var job : Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getAllPeople() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllPeople()
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
                    peopleList.postValue(response.body())
//                    loading.value = false
//                } else {
//                    Log.d("ApiError", "ResponseError")
//                }
//            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}