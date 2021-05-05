package com.exercise.domain.entity

data class FlightOption(
    val logoUrl: String,
    val timeOut: String,
    val timeIn: String,
    val origin: String,
    val destination: String,
    val airlineName: String,
    val duration: String,
    val typeOfFlight: String = "Direct" // better to use enum with resource id, which is associated with specific type
)