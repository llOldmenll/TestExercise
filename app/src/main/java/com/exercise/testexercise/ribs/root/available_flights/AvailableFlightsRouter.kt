package com.exercise.testexercise.ribs.root.available_flights

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link AvailableFlightsBuilder.AvailableFlightsScope}.
 */
class AvailableFlightsRouter(
    view: AvailableFlightsView,
    interactor: AvailableFlightsInteractor,
    component: AvailableFlightsBuilder.Component
) : ViewRouter<AvailableFlightsView, AvailableFlightsInteractor, AvailableFlightsBuilder.Component>(
    view, interactor, component)