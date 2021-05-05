package com.exercise.testexercise.ribs.root

import com.exercise.domain.entity.FlightDetails
import com.exercise.testexercise.ribs.splash.SplashInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootScope].
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {

    @Inject
    lateinit var presenter: RootPresenter

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        router.attachSplashScreen()
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface RootPresenter

    inner class SplashListener : SplashInteractor.Listener {
        override fun flightsObtained(flights: List<FlightDetails>) {
            router.detachCurrentChild()
            router.attachAvailableFlights(flights)
        }
    }
}
