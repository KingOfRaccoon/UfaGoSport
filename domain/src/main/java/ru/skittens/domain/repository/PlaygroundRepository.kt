package ru.skittens.domain.repository

import ru.skittens.domain.entity.ClassPlayground
import ru.skittens.domain.entity.Country
import ru.skittens.domain.entity.Playground
import ru.skittens.domain.entity.TypePlayground
import ru.skittens.domain.util.Resource

interface PlaygroundRepository {
    suspend fun getCountries(): Resource<List<Country>>
    suspend fun getPlaygrounds(): Resource<List<Playground>>
    suspend fun getTypes(): Resource<List<TypePlayground>>
    suspend fun getClasses(): Resource<List<ClassPlayground>>
}