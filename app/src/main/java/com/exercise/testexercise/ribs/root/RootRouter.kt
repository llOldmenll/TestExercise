package com.exercise.testexercise.ribs.root

import com.exercise.domain.entity.FlightDetails
import com.exercise.testexercise.ribs.root.available_flights.AvailableFlightsBuilder
import com.exercise.testexercise.ribs.splash.SplashBuilder
import com.uber.rib.core.Router

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 */
class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val splashBuilder: SplashBuilder,
    private val availableFlightsBuilder: AvailableFlightsBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

    private var currentChild: Router<*, *>? = null

    fun attachSplashScreen() = attachCurrentChild(splashBuilder.build(view))

    fun attachAvailableFlights(flights: List<FlightDetails>) =
        attachCurrentChild(availableFlightsBuilder.build(view, flights))

    fun detachCurrentChild() {
        currentChild?.let {
            detachChild(it)
            if (it is ViewRouter<*, *, *>) view.removeView(it.view)
            currentChild = null
        }
    }

    private fun attachCurrentChild(child: Router<*, *>?) {
        currentChild = child.let {
            attachChild(it)
            if (it is ViewRouter<*, *, *>) view.addView(it.view)
            it
        }
    }

    override fun handleBackPress(): Boolean = currentChild?.handleBackPress() ?: false
}