package ru.skittens.domain.repository

import ru.skittens.domain.entity.Duel
import ru.skittens.domain.entity.DuelsData
import ru.skittens.domain.entity.LoginResponse
import ru.skittens.domain.entity.Rating
import ru.skittens.domain.entity.RatingResponse
import ru.skittens.domain.entity.RegistrationResponse
import ru.skittens.domain.entity.SuccessMessage
import ru.skittens.domain.entity.User
import ru.skittens.domain.util.Resource

interface AuthRepository {
    suspend fun authUser(loginResponse: LoginResponse): Resource<User>
    suspend fun registrationUser(registrationResponse: RegistrationResponse): Resource<User>
    suspend fun getRating(ratingResponse: RatingResponse): Resource<Rating>

    suspend fun getFriends(userId: Int): Resource<List<Int>>
    suspend fun getProfile(userId: Int): Resource<User>
    suspend fun addDuels(duelsData: DuelsData): Resource<SuccessMessage>
    suspend fun getDuels(): Resource<List<Duel>>
}