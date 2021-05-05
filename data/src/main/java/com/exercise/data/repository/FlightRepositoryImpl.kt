package com.exercise.data.repository

import com.exercise.data.entity.FlightsEntity
import com.exercise.data.network.service.FlightNetworkService
import com.exercise.domain.entity.FlightDetails
import com.exercise.domain.mapper.Mapper
import com.exercise.domain.repository.FlightRepository
import io.reactivex.Single

class FlightRepositoryImpl(
    private val flightNetworkService: FlightNetworkService,
    private val flightsAvailabilityMapper: Mapper<FlightsEntity, List<FlightDetails>>,
) : FlightRepository {

    override fun getAvailableFlights(): Single<List<FlightDetails>> =
        flightNetworkService.getFlights()
            .map { flightsAvailabilityMapper.map(it) }
}