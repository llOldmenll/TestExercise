package com.exercise.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItineraryEntity(
    @field:Json(name = "agent")
    val agent: String = "",
    @field:Json(name = "agent_rating")
    val agentRating: Double = 0.0,
    @field:Json(name = "id")
    val id: String = "",
    @field:Json(name = "legs")
    val legs: List<String> = listOf(),
    @field:Json(name = "price")
    val price: String = ""
)