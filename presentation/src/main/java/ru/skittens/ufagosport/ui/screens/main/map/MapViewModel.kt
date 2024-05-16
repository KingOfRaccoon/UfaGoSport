package ru.skittens.ufagosport.ui.screens.main.map

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.skittens.domain.entity.Playground
import ru.skittens.domain.usecase.PlaygroundUseCase

class MapViewModel(private val playgroundUseCase: PlaygroundUseCase): ViewModel() {
    private val _selectedPlaygroundFlow = MutableStateFlow<Playground?>(null)
    val selectedPlaygroundFlow = _selectedPlaygroundFlow.asStateFlow()

    fun getPlaygrounds() = playgroundUseCase.getPlaygrounds()

    fun setPlayground(playground: Playground){
        _selectedPlaygroundFlow.update { playground }
    }

    fun clearPlayground(){
        _selectedPlaygroundFlow.update { null }
    }

    suspend fun loadData() = playgroundUseCase.loadData()
}