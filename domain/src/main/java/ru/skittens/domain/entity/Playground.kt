package ru.skittens.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Playground(
    val address: String,
    val author: Author,
    val can_edit: Boolean,
    val city_id: Int,
    val class_id: Int,
    val comments_count: Int,
    val country_id: Int,
    val create_date: String,
    val equipment_ids: List<Int>,
    val id: Int,
    val latitude: String,
    val longitude: String,
    val mine: Boolean,
    val modify_date: String,
    val name: String,
    val photos: List<Photo>,
    val preview: String,
    val trainings: Int,
    val type_id: Int,
    val users_train_here: List<UsersTrainHere>,
    var city: City? = null,
    var country: Country? = null,
    var classPlayground: ClassPlayground? = null,
    var typePlayground: TypePlayground? = null
)