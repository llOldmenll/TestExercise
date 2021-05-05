package com.exercise.testexercise.ribs.root.available_flights

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.testexercise.ribs.root.available_flights.adapter.AvailableFlightsAdapter
import com.exercise.domain.entity.FlightDetails
import kotlinx.android.synthetic.main.rib_available_flights.view.*

/**
 * Top level view for {@link AvailableFlightsBuilder.AvailableFlightsScope}.
 */
class AvailableFlightsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : ConstraintLayout(context, attrs, defStyle),
    AvailableFlightsInteractor.AvailableFlightsPresenter {

    private val availableFlightsAdapter = AvailableFlightsAdapter()

    override fun onFinishInflate() {
        super.onFinishInflate()

        initUI()
    }

    private fun initUI() {
        vRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = availableFlightsAdapter
        }
    }

    override fun updateFlights(flightDetailsList: List<FlightDetails>) {
        availableFlightsAdapter.flightOptions = ArrayList(flightDetailsList)
        vFlightsNotFound.visibility = if (flightDetailsList.isEmpty()) VISIBLE else GONE
    }
}
