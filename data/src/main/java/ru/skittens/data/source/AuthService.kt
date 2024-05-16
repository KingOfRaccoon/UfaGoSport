package ru.skittens.data.source

import ru.skittens.data.util.Postman
import ru.skittens.domain.entity.User
import ru.skittens.domain.entity.LoginResponse
import ru.skittens.domain.entity.RegistrationResponse
import ru.skittens.domain.util.Resource

class AuthService(private val postman: Postman) {
    val baseUrl = "http://213.171.10.242/"
    val registrationTag = "registration"
    val loginTag = "login"

    suspend fun registrationUser(registrationResponse: RegistrationResponse): Resource<User> {
        return postman.post(baseUrl, registrationTag, registrationResponse)
    }

    suspend fun loginUser(loginResponse: LoginResponse): Resource<User>{
        return postman.post(baseUrl, loginTag, loginResponse)
    }
}