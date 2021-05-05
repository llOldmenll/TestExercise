package com.exercise.testexercise.ribs.splash

import com.exercise.domain.entity.FlightDetails
import com.exercise.domain.use_case.GetFlightDetailsUseCase
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Coordinates Business Logic for [SplashScope].
 */
@RibInteractor
class SplashInteractor : Interactor<SplashInteractor.SplashPresenter, SplashRouter>() {

    @Inject
    lateinit var presenter: SplashPresenter

    @Inject
    lateinit var ribListener: Listener

    @Inject
    lateinit var getFlightsUseCase: GetFlightDetailsUseCase

    private val compositeDisposable = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        compositeDisposable.add(
            getFlightsUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    ribListener.flightsObtained(it)
                }, { presenter.showError(it) {} })
        )
    }

    override fun willResignActive() {
        compositeDisposable.clear()
        super.willResignActive()
    }

    interface Listener {
        fun flightsObtained(flights: List<FlightDetails>)
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface SplashPresenter {
        fun showError(error: Throwable, onDismiss: () -> Unit)
    }
}