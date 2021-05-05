package com.exercise.domain.entity

data class FlightDetails(
    val price: String,
    val agent: String,
    val flightOptions: List<FlightOption>
)