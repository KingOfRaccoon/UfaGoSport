package ru.skittens.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class TypePlayground(
    val id: String,
    val name: String
)