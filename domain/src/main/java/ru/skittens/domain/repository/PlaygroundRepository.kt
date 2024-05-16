package ru.skittens.domain.repository

import ru.skittens.domain.entity.Country
import ru.skittens.domain.entity.Playground
import ru.skittens.domain.util.Resource

interface PlaygroundRepository {
    suspend fun getCountries(): Resource<List<Country>>

    suspend fun getPlaygrounds(): Resource<List<Playground>>
}