package ru.skittens.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val elo: String
)