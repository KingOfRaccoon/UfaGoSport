package ru.skittens.ufagosport.ui.screens.main.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.skittens.domain.entity.DuelsData
import ru.skittens.domain.entity.Playground
import ru.skittens.domain.entity.SuccessMessage
import ru.skittens.domain.usecase.PlaygroundUseCase
import ru.skittens.domain.usecase.UserUseCase
import ru.skittens.domain.util.Resource

class MapViewModel(
    private val playgroundUseCase: PlaygroundUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {
    private val _selectedPlaygroundFlow = MutableStateFlow<Playground?>(null)
    val selectedPlaygroundFlow = _selectedPlaygroundFlow.asStateFlow()

    fun getPlaygrounds() = playgroundUseCase.getPlaygrounds()

    fun setPlayground(playground: Playground) {
        _selectedPlaygroundFlow.update { playground }
    }

    fun clearPlayground() {
        _selectedPlaygroundFlow.update { null }
    }

    val addState = MutableStateFlow<Resource<SuccessMessage>>(Resource.Loading())

    fun addDuel() {
        viewModelScope.launch {
            selectedPlaygroundFlow.value?.let { playground ->
                addState.update {
                    userUseCase.addDuels(
                        DuelsData(
                            userUseCase.user.value.data?.id,
                            "Новое мероприятие от пользователя ${userUseCase.user.value.data?.email}",
                            "false",
                            "Новое мероприятие от пользователя ${userUseCase.user.value.data?.email}",
                            "1",
                            "5",
                            playground.id.toString(),
                            "1"
                        )
                    )
                }
            }
        }
    }

    suspend fun loadData() = playgroundUseCase.loadData()
}