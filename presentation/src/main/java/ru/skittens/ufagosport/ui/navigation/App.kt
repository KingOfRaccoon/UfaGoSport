package ru.skittens.ufagosport.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.skittens.ufagosport.ui.screens.main.MainScreen
import ru.skittens.ufagosport.ui.screens.start.auth.AuthenticationScreen
import ru.skittens.ufagosport.ui.screens.start.registration.RegistrationScreen
import ru.skittens.ufagosport.ui.theme.UfaGoSportTheme

typealias NavigationFun = (Destinations) -> Unit

@Composable
fun App() {
    UfaGoSportTheme {
        val navController = rememberNavController()
        NavHost(navController, Destinations.Authentication.name, Modifier.fillMaxSize()) {
            composable(Destinations.Authentication) {
                AuthenticationScreen(navController::navigate)
            }

            composable(Destinations.Registration) {
                RegistrationScreen(navController::navigate)
            }

            composable(Destinations.Main) {
                MainScreen()
            }
        }
    }
}

fun NavHostController.navigate(destinations: Destinations) {
    navigate(destinations.name)
}

fun NavGraphBuilder.composable(
    destinations: Destinations,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(destinations.name, content = content)
}