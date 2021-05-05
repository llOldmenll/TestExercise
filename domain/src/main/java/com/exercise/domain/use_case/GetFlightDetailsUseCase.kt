package com.exercise.domain.use_case

import com.exercise.domain.entity.FlightDetails
import com.exercise.domain.repository.FlightRepository
import com.exercise.domain.use_case.base.SingleUseCase
import io.reactivex.Single

class GetFlightDetailsUseCase(
    private val flightRepository: FlightRepository
) : SingleUseCase<Unit, List<FlightDetails>> {
    override fun execute(data: Unit?): Single<List<FlightDetails>> =
        flightRepository.getAvailableFlights()
}