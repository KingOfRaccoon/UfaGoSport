package ru.skittens.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class DuelsData(
    val creatorId: Int?,
    val description: String,
    val isOfficially: String,
    val name: String,
    val password: String,
    val playersCount: String,
    val playgroundId: String,
    val type: String
)