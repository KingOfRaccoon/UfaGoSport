package ru.skittens.domain.usecase

import com.sun.tools.javac.Main
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
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

class UserUseCase(private val authRepository: AuthRepository) {
    private val _user = MutableStateFlow<Resource<User>>(Resource.Loading())
    val user = _user.asStateFlow()

    private val _rating = MutableStateFlow<Resource<Rating>>(Resource.Loading())
    val rating = _rating.asStateFlow()

    private val _ratingFriendsFlow = MutableStateFlow<Map<Int, Resource<Rating>>>(hashMapOf())
    val ratingFriendsFlow = _ratingFriendsFlow.asStateFlow()

    private val _friendsFlow = MutableStateFlow<Map<Int, List<Resource<User>>>>(hashMapOf())
    val friendsFlow = _friendsFlow.asStateFlow()

    val duels = MutableStateFlow<List<Duel>>(listOf())
    fun getUserWithRating() = combine(user, rating) { user, rating ->
        when (user) {
            is Resource.Error -> Resource.Error(user.message)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(
                user.data.copy(rating = rating.data ?: Rating("800"))
            )
        }
    }

    fun getFriendsCurrentUser(userId: Int) = friendsFlow.map { it[userId] }

    suspend fun loadFriends(userId: Int) {
        coroutineScope {
            authRepository.getFriends(userId).also {
                if (it is Resource.Success) {
                    _ratingFriendsFlow.update { oldValue ->
                        oldValue + it.data.zip(it.data.map {
                            async {
                                authRepository.getRating(
                                    RatingResponse(it.toString())
                                )
                            }
                        }
                            .awaitAll())
                    }

                    _friendsFlow.update { oldValue ->
                        oldValue + (userId to it.data.map { async { authRepository.getProfile(it) } }
                            .awaitAll())
                    }
                }
            }
        }
    }

    suspend fun addDuels(duelsData: DuelsData): Resource<SuccessMessage> {
        return authRepository.addDuels(duelsData)
    }

    private suspend fun getDuels(){
        duels.update {
            (it + authRepository.getDuels().data.orEmpty()).distinctBy { it.id }
        }
    }
    init {
        MainScope().launch {
            getDuels()
        }
    }

    fun getUsersFriends() =
        combine(user, ratingFriendsFlow, friendsFlow) { user, ratings, friends ->
            friends[user.data?.id]?.map {
                ratings[it.data?.id]?.data?.let { it1 ->
                    it.data?.copy(
                        rating = it1
                    )
                }
            }
        }

    suspend fun loginUser(loginResponse: LoginResponse): Boolean {
        return _user.updateAndGet { authRepository.authUser(loginResponse) } is Resource.Success
    }

    suspend fun registrationUser(registrationResponse: RegistrationResponse) {
        _user.update { authRepository.registrationUser(registrationResponse) }
    }

    suspend fun getRating(ratingResponse: RatingResponse) {
        _rating.update { authRepository.getRating(ratingResponse) }
    }
}