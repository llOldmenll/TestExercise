package com.exercise.data.network.mapper

import com.exercise.data.entity.FlightOptionEntity
import com.exercise.data.entity.FlightsEntity
import com.exercise.domain.entity.FlightDetails
import com.exercise.domain.entity.FlightOption
import com.exercise.domain.mapper.Mapper

class FlightsEntityToFlightDetailsListMapper(
    private val flightOptionMapper: Mapper<FlightOptionEntity, FlightOption>
) : Mapper<FlightsEntity, List<FlightDetails>> {

    override fun map(from: FlightsEntity): List<FlightDetails> {
        val flightDetails = mutableListOf<FlightDetails>()

        from.itineraries.forEach { itinerary ->
            val itineraryFlights = mutableListOf<FlightOption>()
            from.legs.filter { itinerary.legs.contains(it.id) }
                .forEach { itineraryFlights.add(flightOptionMapper.map(it)) }
            flightDetails.add(FlightDetails(itinerary.price, itinerary.agent, itineraryFlights))
        }

        return flightDetails
    }
}