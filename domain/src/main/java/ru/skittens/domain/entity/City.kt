package ru.skittens.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class City(
    val id: String,
    val lat: String,
    val lon: String,
    val name: String
)