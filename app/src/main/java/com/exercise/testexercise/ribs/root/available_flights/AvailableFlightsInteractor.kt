package com.exercise.testexercise.ribs.root.available_flights

import com.exercise.domain.entity.FlightDetails
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [AvailableFlightsScope].
 */
@RibInteractor
class AvailableFlightsInteractor :
    Interactor<AvailableFlightsInteractor.AvailableFlightsPresenter, AvailableFlightsRouter>() {

    @Inject
    lateinit var presenter: AvailableFlightsPresenter
    @Inject
    lateinit var flights: List<FlightDetails>

    private val compositeDisposable = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        initUI()
    }

    override fun willResignActive() {
        compositeDisposable.clear()
        super.willResignActive()
    }

    private fun initUI() {
        presenter.updateFlights(flights)
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface AvailableFlightsPresenter {
        fun updateFlights(flightDetailsList: List<FlightDetails>)
    }
}
