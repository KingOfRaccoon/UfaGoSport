package ru.skittens.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val id: Int,
    val image: String,
    val lang: String,
    val name: String,
    val purchase_customer_editor: Boolean
)