package com.exercise.testexercise.ribs.root.available_flights.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exercise.domain.entity.FlightDetails
import com.exercise.testexercise.R
import kotlinx.android.synthetic.main.item_available_flight.view.*

class AvailableFlightsAdapter :
    RecyclerView.Adapter<AvailableFlightsAdapter.AvailableFlightsHolder>() {
    var flightOptions = arrayListOf<FlightDetails>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableFlightsHolder {
        return AvailableFlightsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_available_flight, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AvailableFlightsHolder, position: Int) =
        holder.bindView(flightOptions[position])

    override fun getItemCount(): Int = flightOptions.count()

    inner class AvailableFlightsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(flightDetail: FlightDetails) {
            itemView.apply {
                flightDetail.apply {

                    vPrice.text = price
                    vAgent.text = agent

                    // View creation should be done dynamically, bs of lack of time it's hardcoded
                    Glide.with(context).apply {
                        load(flightOptions.getOrNull(0)?.logoUrl ?: "").into(vPlaneIconFirst)
                        load(flightOptions.getOrNull(1)?.logoUrl ?: "").into(vPlaneIconSecond)
                    }

                    flightOptions.getOrNull(0)?.let {
                        val time = "${it.timeOut} - ${it.timeOut}"
                        val itinerary = "${it.origin}-${it.destination}, ${it.airlineName}"
                        vTimeFirst.text = time
                        vItineraryFirst.text = itinerary
                        vFlightTypeFirst.text = it.typeOfFlight
                        vDurationFirst.text = it.duration
                    }

                    flightOptions.getOrNull(1)?.let {
                        val time = "${it.timeOut} - ${it.timeOut}"
                        val itinerary = "${it.origin}-${it.destination}, ${it.airlineName}"
                        vTimeSecond.text = time
                        vItinerarySecond.text = itinerary
                        vFlightTypeSecond.text = it.typeOfFlight
                        vDurationSecond.text = it.duration
                    }
                }
            }
        }
    }
}