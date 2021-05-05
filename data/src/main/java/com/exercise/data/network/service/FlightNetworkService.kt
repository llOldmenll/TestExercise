package com.exercise.data.network.service

import com.exercise.data.entity.FlightsEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface FlightNetworkService {
    @GET("skyscanner-prod-takehome-test/flights.json")
    fun getFlights(): Single<FlightsEntity>
}