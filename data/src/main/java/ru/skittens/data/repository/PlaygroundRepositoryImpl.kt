package ru.skittens.data.repository

import ru.skittens.data.source.PlaygroundService
import ru.skittens.domain.entity.ClassPlayground
import ru.skittens.domain.entity.Country
import ru.skittens.domain.entity.Playground
import ru.skittens.domain.entity.TypePlayground
import ru.skittens.domain.repository.PlaygroundRepository
import ru.skittens.domain.util.Resource

class PlaygroundRepositoryImpl(private val playgroundService: PlaygroundService) :
    PlaygroundRepository {
    override suspend fun getCountries(): Resource<List<Country>> {
        return playgroundService.getCountries()
    }

    override suspend fun getPlaygrounds(): Resource<List<Playground>> {
        return playgroundService.getPlaygrounds()
    }

    override suspend fun getTypes(): Resource<List<TypePlayground>> {
        return playgroundService.getTypes()
    }

    override suspend fun getClasses(): Resource<List<ClassPlayground>> {
        return playgroundService.getClasses()
    }
}