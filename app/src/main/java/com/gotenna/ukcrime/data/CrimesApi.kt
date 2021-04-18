package com.gotenna.ukcrime.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CrimesApi {

    @GET("/api/crimes-no-location")
    fun crimeNoLocation(@Query("category") category: String, @Query("force") force: String): Call<List<Crime>>

    @GET("api/crimes-at-location")
    fun crimeLocation(@Query("lat") lat: Double, @Query("lng") lng: Double): Call<List<Crime>>

}