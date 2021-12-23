package com.tae.apiimplementation

class MainRepository constructor(private val retrofitService: RetrofitService){

    suspend fun getAllPeople() = retrofitService.getAllPeople()

}