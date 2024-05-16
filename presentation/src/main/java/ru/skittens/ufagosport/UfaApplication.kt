package ru.skittens.ufagosport

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import ru.skittens.data.source.AuthService
import ru.skittens.data.source.PlaygroundService
import ru.skittens.data.util.Postman
import ru.skittens.ufagosport.ui.screens.main.friends.FriendsViewModel
import ru.skittens.ufagosport.ui.screens.main.map.MapViewModel
import ru.skittens.ufagosport.ui.screens.main.news.NewsScreen
import ru.skittens.ufagosport.ui.screens.main.news.NewsViewModel
import ru.skittens.ufagosport.ui.screens.main.profile.ProfileViewModel

class UfaApplication : Application() {
    private val listModules = listOf(
        module {
            single { Postman() }
            single { PlaygroundService(get()) }
            single { AuthService(get()) }
            single { MapViewModel() }
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