package ru.skittens.domain.usecase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import ru.skittens.domain.entity.LoginResponse
import ru.skittens.domain.entity.RegistrationResponse
import ru.skittens.domain.entity.User
import ru.skittens.domain.repository.AuthRepository
import ru.skittens.domain.util.Resource

class UserUseCase(private val authRepository: AuthRepository) {
    private val _user = MutableStateFlow<Resource<User>>(Resource.Loading())
    val user = _user.asStateFlow()

    suspend fun loginUser(loginResponse: LoginResponse): Boolean {
        return _user.updateAndGet { authRepository.authUser(loginResponse) } is Resource.Success
    }

    suspend fun registrationUser(registrationResponse: RegistrationResponse) {
        _user.update { authRepository.registrationUser(registrationResponse) }
    }
}