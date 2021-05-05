package com.exercise.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlightOptionEntity(
    @field:Json(name = "airline_id")
    val airlineId: String = "",
    @field:Json(name = "airline_name")
    val airlineName: String = "",
    @field:Json(name = "arrival_airport")
    val arrivalAirport: String = "",
    @field:Json(name = "arrival_time")
    val arrivalTime: String = "",
    @field:Json(name = "departure_airport")
    val departureAirport: String = "",
    @field:Json(name = "departure_time")
    val departureTime: String = "",
    @field:Json(name = "duration_mins")
    val durationMins: Int = 0,
    @field:Json(name = "id")
    val id: String = "",
    @field:Json(name = "stops")
    val stops: Int = 0
)