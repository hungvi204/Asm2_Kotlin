package com.example.pd07689_kot104_asm.request.service

object RetrofitInstance {
    private const val BASE_URL = "http://192.168.1.64:3000/api/"

    val apiService: ApiService by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}