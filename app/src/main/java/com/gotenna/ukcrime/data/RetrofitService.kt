package com.gotenna.ukcrime.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val baseUrl = "https://data.police.uk"
    private val httpClient = OkHttpClient.Builder()
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    /**
     * Function to generate the retrofit service
     */
    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }
}