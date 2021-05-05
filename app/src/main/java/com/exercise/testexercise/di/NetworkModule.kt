package com.exercise.testexercise.di

import com.exercise.data.network.OkHttpClientFactory
import com.exercise.data.network.OkHttpClientFactoryImpl
import com.exercise.data.network.ConverterFactory
import com.exercise.data.network.ConverterFactoryImpl
import com.exercise.data.network.CallAdapterFactory
import com.exercise.data.network.CallAdapterFactoryImpl
import com.exercise.data.network.NetworkServiceFactory
import com.exercise.data.network.NetworkServiceFactoryImpl
import com.exercise.testexercise.BuildConfig
import dagger.Module
import dagger.Provides

@Module
abstract class NetworkModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        internal fun interceptorsFactory(): OkHttpClientFactory = OkHttpClientFactoryImpl()

        @Provides
        @JvmStatic
        internal fun converterFactory(): ConverterFactory = ConverterFactoryImpl()

        @Provides
        @JvmStatic
        internal fun callAdapterFactory(): CallAdapterFactory = CallAdapterFactoryImpl()

        @Provides
        @JvmStatic
        internal fun networkServiceFactory(
            okHttpClientFactory: OkHttpClientFactory,
            converterFactory: ConverterFactory,
            callAdapterFactory: CallAdapterFactory,
        ): NetworkServiceFactory = NetworkServiceFactoryImpl(
            BuildConfig.BASE_URL,
            okHttpClientFactory,
            converterFactory,
            callAdapterFactory
        )
    }
}