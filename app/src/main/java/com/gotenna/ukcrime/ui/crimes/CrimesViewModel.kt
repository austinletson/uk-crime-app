package com.gotenna.ukcrime.ui.crimes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gotenna.ukcrime.data.Crime
import com.gotenna.ukcrime.data.CrimeRepository

class CrimesViewModel: ViewModel() {

    private var crimesList = MutableLiveData<List<Crime>>()

    fun getCrimes(): MutableLiveData<List<Crime>>? {
        return crimesList
    }


    // Consider caching
    fun getCrimesNoLocationLondon(): MutableLiveData<List<Crime>> {
        return CrimeRepository.getCrimesNoLocation("all-crime", "city-of-london")
    }

    fun getCrimesLocation(): MutableLiveData<List<Crime>> {
        //Hardcoded location for testing purposes.
        return CrimeRepository.getCrimesLocation(52.629729, -1.131592)
    }
}