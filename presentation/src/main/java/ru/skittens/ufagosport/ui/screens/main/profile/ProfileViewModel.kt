package ru.skittens.ufagosport.ui.screens.main.profile

import androidx.compose.ui.util.fastAll
import androidx.compose.ui.util.fastFirst
import androidx.compose.ui.util.fastFirstOrNull
import androidx.lifecycle.ViewModel
import ru.skittens.domain.entity.User
import ru.skittens.domain.usecase.UserUseCase
import ru.skittens.ufagosport.ui.screens.start.registration.Avatar

class ProfileViewModel(private val userUseCase: UserUseCase): ViewModel() {
    fun getUser() = userUseCase.getUserWithRating()

    fun getAvatar(user: User?) = Avatar.entries.toList().fastFirstOrNull { it.name == user?.photo } ?: Avatar.First
}