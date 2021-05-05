package com.exercise.data.network

import com.exercise.data.network.adapter.NullToEmptyStringAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

class ConverterFactoryImpl : ConverterFactory {

    private val moshi by lazy(LazyThreadSafetyMode.NONE) {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(NullToEmptyStringAdapter())
            .build()
    }

    override fun create(): Converter.Factory = MoshiConverterFactory.create(moshi)
}