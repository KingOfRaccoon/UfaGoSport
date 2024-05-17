package ru.skittens.data.repository

import ru.skittens.data.source.AuthService
import ru.skittens.domain.entity.Duel
import ru.skittens.domain.entity.DuelsData
import ru.skittens.domain.entity.LoginResponse
import ru.skittens.domain.entity.Rating
import ru.skittens.domain.entity.RatingResponse
import ru.skittens.domain.entity.RegistrationResponse
import ru.skittens.domain.entity.SuccessMessage
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

    override suspend fun getRating(ratingResponse: RatingResponse): Resource<Rating> {
        return authService.getRating(ratingResponse)
    }

    override suspend fun getFriends(userId: Int): Resource<List<Int>> {
        return authService.getFriends(userId)
    }

    override suspend fun getProfile(userId: Int): Resource<User> {
        return authService.getProfile(userId)
    }

    override suspend fun addDuels(duelsData: DuelsData): Resource<SuccessMessage> {
        return authService.addDuels(duelsData)
    }

    override suspend fun getDuels(): Resource<List<Duel>> {
        return authService.getDuels()
    }
}