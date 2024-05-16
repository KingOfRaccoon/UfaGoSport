package ru.skittens.domain.repository

import ru.skittens.domain.entity.LoginResponse
import ru.skittens.domain.entity.RegistrationResponse
import ru.skittens.domain.entity.User
import ru.skittens.domain.entity.UsersTrainHere
import ru.skittens.domain.util.Resource

interface AuthRepository {
    suspend fun authUser(loginResponse: LoginResponse): Resource<User>
    suspend fun registrationUser(registrationResponse: RegistrationResponse): Resource<User>
}