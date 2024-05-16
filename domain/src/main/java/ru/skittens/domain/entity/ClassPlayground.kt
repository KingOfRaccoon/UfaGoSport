package ru.skittens.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class ClassPlayground(
    val id: String,
    val name: String
)