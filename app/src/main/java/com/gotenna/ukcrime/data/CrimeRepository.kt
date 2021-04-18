package com.gotenna.ukcrime.data

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CrimeRepository {

    private val crimesApi = RetrofitService.createService(CrimesApi::class.java)

    fun getCrimesNoLocation(category: String, force: String): MutableLiveData<List<Crime>> {
       val crimesList = MutableLiveData<List<Crime>>()

        crimesApi.crimeNoLocation(category, force).enqueue(object : Callback<List<Crime>>{
            override fun onFailure(call: Call<List<Crime>>, t: Throwable) {
                crimesList.postValue(null)
            }

            override fun onResponse(call: Call<List<Crime>>, response: Response<List<Crime>>) {
                if (response.isSuccessful){
                    crimesList.postValue(response.body())
                } else {
                    crimesList.postValue(null)
                }
            }

        })
        return crimesList
    }

    fun getCrimesLocation(lat: Double, lng: Double): MutableLiveData<List<Crime>> {
        val crimesList = MutableLiveData<List<Crime>>()

        crimesApi.crimeLocation(lat, lng).enqueue(object : Callback<List<Crime>>{
            override fun onFailure(call: Call<List<Crime>>, t: Throwable) {
                crimesList.postValue(null)
            }

            override fun onResponse(call: Call<List<Crime>>, response: Response<List<Crime>>) {
                if (response.isSuccessful){
                    crimesList.postValue(response.body())
                } else {
                    crimesList.postValue(null)
                }
            }

        })
        return crimesList
    }

}