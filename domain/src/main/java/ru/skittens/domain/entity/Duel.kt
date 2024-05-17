package ru.skittens.domain.entity

data class Duel(
    val creatorId: Int,
    val description: String,
    val id: Int,
    val isOfficially: Boolean,
    val name: String,
    val password: String,
    val playersCount: Int,
    val playground: Int,
    val type: String
)