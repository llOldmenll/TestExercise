package com.exercise.testexercise.ribs.root.available_flights

import android.view.LayoutInflater
import android.view.ViewGroup

import com.exercise.domain.entity.FlightDetails
import com.exercise.testexercise.R
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Scope

/**
 * Builder for the {@link AvailableFlightsScope}..
 */
class AvailableFlightsBuilder(dependency: ParentComponent) :
    ViewBuilder<AvailableFlightsView, AvailableFlightsRouter, AvailableFlightsBuilder.ParentComponent>(
        dependency
    ) {

    /**
     * Builds a new [AvailableFlightsRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [AvailableFlightsRouter].
     */
    fun build(
        parentViewGroup: ViewGroup,
        flights: List<FlightDetails>
    ): AvailableFlightsRouter {
        val view = createView(parentViewGroup)
        val interactor = AvailableFlightsInteractor()
        val component = DaggerAvailableFlightsBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .flights(flights)
            .build()
        return component.availableflightsRouter()
    }

    override fun inflateView(
        inflater: LayoutInflater,
        parentViewGroup: ViewGroup,
    ): AvailableFlightsView {
        return inflater.inflate(
            R.layout.rib_available_flights,
            parentViewGroup,
            false
        ) as AvailableFlightsView
    }

    interface ParentComponent

    @dagger.Module
    abstract class Module {

        @AvailableFlightsScope
        @Binds
        internal abstract fun presenter(view: AvailableFlightsView): AvailableFlightsInteractor.AvailableFlightsPresenter

        @dagger.Module
        companion object {

            @AvailableFlightsScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: AvailableFlightsView,
                interactor: AvailableFlightsInteractor,
            ): AvailableFlightsRouter = AvailableFlightsRouter(
                view,
                interactor,
                component
            )
        }
    }

    @AvailableFlightsScope
    @dagger.Component(
        modules = [Module::class],
        dependencies = [ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<AvailableFlightsInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: AvailableFlightsInteractor): Builder

            @BindsInstance
            fun view(view: AvailableFlightsView): Builder

            @BindsInstance
            fun flights(flights: List<FlightDetails>): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun availableflightsRouter(): AvailableFlightsRouter
    }

    @Scope
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    internal annotation class AvailableFlightsScope
}
