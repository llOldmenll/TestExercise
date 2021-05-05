package com.exercise.data.network

import retrofit2.Retrofit

class NetworkServiceFactoryImpl(
    private val baseUrl: String,
    private val okHttpClient: OkHttpClientFactory,
    private val converterFactory: ConverterFactory,
    private val callAdapterFactory: CallAdapterFactory
) : NetworkServiceFactory {

    private val retrofit by lazy(LazyThreadSafetyMode.NONE) {
        Retrofit.Builder()
            .client(okHttpClient.create())
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory.create())
            .addCallAdapterFactory(callAdapterFactory.create())
            .build()
    }

    override fun <T> create(serviceType: Class<out T>): T = retrofit.create(serviceType)
}