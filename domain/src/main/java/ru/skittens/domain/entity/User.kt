package ru.skittens.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val id: Int,
    val photo: String,
    val role: Int,
    val username: String,
    var rating: Rating = Rating("0")
)