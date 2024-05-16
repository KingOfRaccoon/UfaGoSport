package ru.skittens.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationResponse(
    val mail: String,
    val password: String,
    val photo: String,
    val role: String,
    val username: String
)