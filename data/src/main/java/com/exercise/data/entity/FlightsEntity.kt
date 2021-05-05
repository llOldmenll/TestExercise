package com.exercise.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlightsEntity(
    @field:Json(name = "itineraries")
    val itineraries: List<ItineraryEntity>,
    @field:Json(name = "legs")
    val legs: List<FlightOptionEntity>
)