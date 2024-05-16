package ru.skittens.domain.usecase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import ru.skittens.domain.entity.ClassPlayground
import ru.skittens.domain.entity.Country
import ru.skittens.domain.entity.Playground
import ru.skittens.domain.entity.TypePlayground
import ru.skittens.domain.repository.PlaygroundRepository
import ru.skittens.domain.util.Resource

class PlaygroundUseCase(private val playgroundRepository: PlaygroundRepository) {
    private val _playgroundsFlow = MutableStateFlow<Resource<List<Playground>>>(Resource.Loading())
    val playgroundsFlow = _playgroundsFlow.asStateFlow()

    private val _countryFlow = MutableStateFlow<Resource<List<Country>>>(Resource.Loading())
    val countryFlow = _countryFlow.asStateFlow()

    private val _typesFlow = MutableStateFlow<Resource<List<TypePlayground>>>(Resource.Loading())
    val typesFlow = _typesFlow.asStateFlow()

    private val _classesFlow = MutableStateFlow<Resource<List<ClassPlayground>>>(Resource.Loading())
    val classesFlow = _classesFlow.asStateFlow()

    suspend fun loadData() {
        _playgroundsFlow.update { playgroundRepository.getPlaygrounds() }
        _countryFlow.update { playgroundRepository.getCountries() }
        _typesFlow.update { playgroundRepository.getTypes() }
        _classesFlow.update { playgroundRepository.getClasses() }
    }

    fun getPlaygrounds() = combine(
        playgroundsFlow,
        countryFlow,
        typesFlow,
        classesFlow
    ) { playgrounds, country, types, classes ->
        when (playgrounds) {
            is Resource.Error -> Resource.Error(playgrounds.message)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(playgrounds.data.map { playground ->
                playground.copy(
                    country = country.data?.find { it.id == playground.country_id.toString() },
                    city = country.data?.find { it.id == playground.country_id.toString() }?.cities?.find { it.id == playground.city_id.toString() },
                    typePlayground = types.data?.find { it.id == playground.type_id.toString() },
                    classPlayground = classes.data?.find { it.id == playground.class_id.toString() }
                )
            })
        }
    }
}