package ru.skittens.ufagosport.ui.screens.main.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.skittens.domain.entity.User
import ru.skittens.domain.usecase.UserUseCase
import ru.skittens.domain.util.Resource

class FriendsViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    private val _friendsUserFlow = MutableStateFlow<List<Resource<User>>>(listOf())
    val friendsFlow = _friendsUserFlow.asStateFlow()
    fun getUser() = userUseCase.getUserWithRating()
    fun loadFriends(userId: Int) {
        viewModelScope.launch {
            userUseCase.loadFriends(userId)
        }
    }

    fun getFriends() = userUseCase.getUsersFriends()
}