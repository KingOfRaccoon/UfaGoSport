package ru.skittens.domain.entity

data class User(
    val email: String,
    val id: Int,
    val photo: String,
    val role: Int,
    val username: String
)