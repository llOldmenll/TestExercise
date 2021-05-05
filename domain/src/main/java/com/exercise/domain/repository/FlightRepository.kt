package com.exercise.domain.repository

import com.exercise.domain.entity.FlightDetails
import io.reactivex.Single

/**
 * Used for interaction with flights data
 */
interface FlightRepository {
    /**
     * Get all available flights
     *
     * @return FlightOption
     */
    fun getAvailableFlights(): Single<List<FlightDetails>>
}