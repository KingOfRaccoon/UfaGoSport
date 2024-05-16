package ru.skittens.ufagosport

import android.app.Application
import android.content.Context
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import ru.skittens.data.repository.AuthRepositoryImpl
import ru.skittens.data.repository.PlaygroundRepositoryImpl
import ru.skittens.data.source.AuthService
import ru.skittens.data.source.PlaygroundService
import ru.skittens.data.util.Postman
import ru.skittens.domain.repository.AuthRepository
import ru.skittens.domain.repository.PlaygroundRepository
import ru.skittens.domain.usecase.PlaygroundUseCase
import ru.skittens.domain.usecase.UserUseCase
import ru.skittens.ufagosport.ui.screens.main.friends.FriendsViewModel
import ru.skittens.ufagosport.ui.screens.main.map.MapViewModel
import ru.skittens.ufagosport.ui.screens.start.auth.AuthenticationViewModel
import ru.skittens.ufagosport.ui.screens.main.news.NewsScreen
import ru.skittens.ufagosport.ui.screens.main.news.NewsViewModel
import ru.skittens.ufagosport.ui.screens.main.profile.ProfileViewModel

class UfaApplication : Application() {
    private val listModules = listOf(
        module {
            single { Postman() }
            single { PlaygroundService(get()) }
            single { AuthService(get()) }
            single<AuthRepository> { AuthRepositoryImpl(get()) }
            single<PlaygroundRepository> { PlaygroundRepositoryImpl(get()) }
            single { UserUseCase(get()) }
            single { PlaygroundUseCase(get()) }
            single {
                AuthenticationViewModel(
                    get(),
                    {
                        this@UfaApplication.getSharedPreferences("avatar", Context.MODE_PRIVATE)
                            .getString("avatar", "") ?: ""
                    }
                ) {
                    this@UfaApplication.getSharedPreferences("avatar", Context.MODE_PRIVATE).edit()
                        .putString("avatar", it).apply()
                }
            }
            single { MapViewModel(get()) }
            single { NewsViewModel() }
            single { ProfileViewModel() }
            single { FriendsViewModel() }
        }
    )

    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
        startKoin {
            modules(listModules)
            androidContext(this@UfaApplication)
            androidLogger(Level.ERROR)
        }
    }
}