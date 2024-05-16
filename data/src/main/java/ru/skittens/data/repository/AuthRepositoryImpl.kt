package ru.skittens.data.repository

import ru.skittens.data.source.AuthService
import ru.skittens.domain.entity.LoginResponse
import ru.skittens.domain.entity.RegistrationResponse
import ru.skittens.domain.entity.User
import ru.skittens.domain.repository.AuthRepository
import ru.skittens.domain.util.Resource

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {

    override suspend fun registrationUser(registrationResponse: RegistrationResponse): Resource<User> {
        return authService.registrationUser(registrationResponse)
    }

    override suspend fun authUser(loginResponse: LoginResponse): Resource<User> {
        return authService.loginUser(loginResponse)
    }
}