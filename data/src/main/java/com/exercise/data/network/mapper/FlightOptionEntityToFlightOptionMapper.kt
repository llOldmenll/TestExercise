package com.exercise.data.network.mapper

import com.exercise.data.entity.FlightOptionEntity
import com.exercise.domain.entity.FlightOption
import com.exercise.domain.mapper.Mapper
import com.exercise.domain.utils.toFormattedDate

class FlightOptionEntityToFlightOptionMapper : Mapper<FlightOptionEntity, FlightOption> {
    override fun map(from: FlightOptionEntity): FlightOption = with(from) {
        FlightOption(
            "https://logos.skyscnr.com/images/airlines/small/%s.png".format(airlineId),
            departureTime.toFormattedDate(),
            arrivalTime.toFormattedDate(),
            departureAirport,
            arrivalAirport,
            airlineName,
            "${durationMins / 60}h:${durationMins % 60}m"
        )
    }
}