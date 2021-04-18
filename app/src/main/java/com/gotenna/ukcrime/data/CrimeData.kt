package com.gotenna.ukcrime.data

import java.io.Serializable

/**
 * Data classes to represent crimes
 */
data class Crime(
        val id: Int,
        val category: String,
        val location: Location,
        val month: String,
        val outcome_status: OutcomeStatus
) : Serializable

data class Location(
        val latitude: String,
        val longitude: String,
        val street: Street
) : Serializable

data class Street (
        val id: Int,
        val name: String
) : Serializable

data class OutcomeStatus (
        val category: String,
        val date: String
) : Serializable