package ru.skittens.data.source

import ru.skittens.data.util.Postman
import ru.skittens.domain.entity.Duel
import ru.skittens.domain.entity.DuelsData
import ru.skittens.domain.entity.FriendsResponse
import ru.skittens.domain.entity.User
import ru.skittens.domain.entity.LoginResponse
import ru.skittens.domain.entity.Rating
import ru.skittens.domain.entity.RatingResponse
import ru.skittens.domain.entity.RegistrationResponse
import ru.skittens.domain.entity.SuccessMessage
import ru.skittens.domain.util.Resource

class AuthService(private val postman: Postman) {
    private val baseUrl = "http://213.171.10.242/"
    private val registrationTag = "registration"
    private val loginTag = "login"
    private val ratingTag = "getUserNotOfficialElo"
    private val friendsTag = "friends"
    private val profileTag = "getProfile"
    private val addFriendTag = "followToUser"
    private val duelsTag = "duels"

    suspend fun registrationUser(registrationResponse: RegistrationResponse): Resource<User> {
        return postman.post(baseUrl, registrationTag, registrationResponse)
    }

    suspend fun loginUser(loginResponse: LoginResponse): Resource<User>{
        return postman.post(baseUrl, loginTag, loginResponse)
    }

    suspend fun getRating(ratingResponse: RatingResponse): Resource<Rating>{
        return postman.post(baseUrl, ratingTag, ratingResponse)
    }

    suspend fun getFriends(userId: Int): Resource<List<Int>> {
        return postman.get(baseUrl, friendsTag, arguments = hashMapOf("userId" to userId))
    }

    suspend fun getProfile(userId: Int): Resource<User> {
        return postman.get(baseUrl, profileTag, arguments = hashMapOf("userId" to userId))
    }

    suspend fun addFriend(friendsResponse: FriendsResponse): Resource<SuccessMessage>{
        return postman.post(baseUrl, addFriendTag, friendsResponse)
    }

    suspend fun addDuels(duelsData: DuelsData): Resource<SuccessMessage> {
        return postman.post(baseUrl, duelsTag, duelsData)
    }

    suspend fun getDuels(): Resource<List<Duel>> {
        return postman.get(baseUrl, duelsTag)
    }
}