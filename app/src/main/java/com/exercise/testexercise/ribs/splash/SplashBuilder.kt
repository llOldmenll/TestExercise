package com.exercise.testexercise.ribs.splash

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.exercise.data.entity.FlightsEntity
import com.exercise.data.network.NetworkServiceFactory
import com.exercise.data.network.mapper.FlightOptionEntityToFlightOptionMapper
import com.exercise.data.network.mapper.FlightsEntityToFlightDetailsListMapper
import com.exercise.data.network.service.FlightNetworkService
import com.exercise.data.repository.FlightRepositoryImpl
import com.exercise.domain.entity.FlightDetails
import com.exercise.domain.mapper.Mapper
import com.exercise.domain.repository.FlightRepository
import com.exercise.domain.use_case.GetFlightDetailsUseCase
import com.exercise.testexercise.R
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Scope

/**
 * Builder for the {@link SplashScope}.
 */
class SplashBuilder(dependency: ParentComponent) :
    ViewBuilder<SplashView, SplashRouter, SplashBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [SplashRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [SplashRouter].
     */
    fun build(parentViewGroup: ViewGroup): SplashRouter {
        val view = createView(parentViewGroup)
        val interactor = SplashInteractor()
        val component = DaggerSplashBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.splashRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): SplashView {
        return inflater.inflate(R.layout.rib_splash, parentViewGroup, false) as SplashView
    }

    interface ParentComponent {
        fun context(): Context
        fun splashListener(): SplashInteractor.Listener
        fun testNetworkServiceFactory(): NetworkServiceFactory
    }

    @dagger.Module
    abstract class Module {

        @SplashScope
        @Binds
        internal abstract fun presenter(view: SplashView): SplashInteractor.SplashPresenter

        @dagger.Module
        companion object {

            @SplashScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: SplashView,
                interactor: SplashInteractor
            ): SplashRouter = SplashRouter(view, interactor, component)

            @SplashScope
            @Provides
            @JvmStatic
            internal fun flightNetworkService(
                networkServiceFactory: NetworkServiceFactory
            ): FlightNetworkService = networkServiceFactory.create(FlightNetworkService::class.java)

            @SplashScope
            @Provides
            @JvmStatic
            internal fun flightsMapper(): Mapper<FlightsEntity, List<FlightDetails>> {
                return FlightsEntityToFlightDetailsListMapper(
                    FlightOptionEntityToFlightOptionMapper()
                )
            }

            @SplashScope
            @Provides
            @JvmStatic
            internal fun flightRepository(
                flightNetworkService: FlightNetworkService,
                flightsMapper: Mapper<FlightsEntity, List<FlightDetails>>
            ): FlightRepository = FlightRepositoryImpl(flightNetworkService, flightsMapper)

            @SplashScope
            @Provides
            @JvmStatic
            internal fun getStationsUseCase(
                flightRepository: FlightRepository
            ): GetFlightDetailsUseCase = GetFlightDetailsUseCase(flightRepository)
        }
    }

    @SplashScope
    @dagger.Component(
        modules = [Module::class],
        dependencies = [ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<SplashInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: SplashInteractor): Builder

            @BindsInstance
            fun view(view: SplashView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun splashRouter(): SplashRouter
    }

    @Scope
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    internal annotation class SplashScope
}
