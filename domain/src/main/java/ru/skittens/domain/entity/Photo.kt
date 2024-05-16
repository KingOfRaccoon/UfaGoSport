package ru.skittens.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id: Int,
    val photo: String
)