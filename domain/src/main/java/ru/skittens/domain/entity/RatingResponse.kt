package ru.skittens.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class RatingResponse(
    val userId: String
)