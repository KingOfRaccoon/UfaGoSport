package ru.skittens.ufagosport.ui.screens.start.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.skittens.domain.entity.LoginResponse
import ru.skittens.domain.entity.RegistrationResponse
import ru.skittens.domain.usecase.UserUseCase
import ru.skittens.ufagosport.ui.screens.start.registration.Avatar

class AuthenticationViewModel(private val userUseCase: UserUseCase, getAvatar: () -> String, private val updateAvatar: (String) -> Unit) : ViewModel() {
    private val _loginFlow = MutableStateFlow("")
    val loginFlow = _loginFlow.asStateFlow()

    private val _passwordFlow = MutableStateFlow("")
    val passwordFlow = _passwordFlow.asStateFlow()

    private val _nameFlow = MutableStateFlow("")
    val nameFlow = _nameFlow.asStateFlow()

    private val _userImageFlow = MutableStateFlow(getUserImage(getAvatar))
    val userImageFlow = _userImageFlow.asStateFlow()

    fun getUser() = userUseCase.user

    val navigateToMain = MutableStateFlow<Boolean?>(null)

    fun clearNavigate() {
        navigateToMain.update { null }
    }

    fun updateLogin(newLogin: String) {
        _loginFlow.update { newLogin }
    }

    fun updatePassword(newPassword: String) {
        _passwordFlow.update { newPassword }
    }

    fun updateName(newPassword: String) {
        _nameFlow.update { newPassword }
    }

    fun setUserImage(avatar: Avatar) {
        _userImageFlow.update { avatar }
        updateAvatar(avatar.name)
    }

    fun loginUser() {
        viewModelScope.launch {
            val loginResponse = LoginResponse(loginFlow.value, passwordFlow.value)

            navigateToMain.update { userUseCase.loginUser(loginResponse) }
        }
    }

    fun registrationUser() {
        viewModelScope.launch {
            val loginResponse = RegistrationResponse(loginFlow.value, passwordFlow.value, userImageFlow.value.name, "1", nameFlow.value)

            userUseCase.registrationUser(loginResponse)
        }
    }

    private fun getUserImage(getAvatar: () -> String) =
        Avatar.entries.find { getAvatar() == it.name } ?: Avatar.First
}