package ru.skittens.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val cities: List<City>,
    val id: String,
    val name: String
)