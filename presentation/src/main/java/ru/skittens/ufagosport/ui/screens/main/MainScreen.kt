package ru.skittens.ufagosport.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastFirstOrNull
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.skittens.ufagosport.ui.elements.DisplayLargeText
import ru.skittens.ufagosport.ui.elements.HeadlineLargeText
import ru.skittens.ufagosport.ui.elements.TitleMediumText
import ru.skittens.ufagosport.ui.navigation.Destinations
import ru.skittens.ufagosport.ui.navigation.NavigationFun
import ru.skittens.ufagosport.ui.navigation.composable
import ru.skittens.ufagosport.ui.navigation.navigate
import ru.skittens.ufagosport.ui.screens.main.map.MapScreen

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    val currentEntity = remember(navHostController.currentDestination?.route) {
        Destinations.entries.toList()
            .fastFirstOrNull { it.name == navHostController.currentDestination?.route }
    }

    Scaffold(
        Modifier.fillMaxSize(),
        topBar = { MainTopBar(currentEntity?.title.orEmpty()) },
        bottomBar = { MainBottomNavigationMenu(navHostController::navigate) }) {
        NavHost(navHostController, Destinations.Map.name, Modifier.fillMaxSize().padding(it)) {
            composable(Destinations.Map) {
                MapScreen()
            }

            composable(Destinations.NewsFriends) {
                DisplayLargeText("Булат 2")
            }

            composable(Destinations.Profile) {
                HeadlineLargeText("Булат 3")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String) {
    TopAppBar({ TitleMediumText(title) })
}

@Composable
fun MainBottomNavigationMenu(navigateTo: NavigationFun) {
    var currentIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(Modifier.fillMaxWidth()) {
        NavigationBarItem(
            0 == currentIndex,
            { currentIndex = 0; navigateTo(Destinations.Map) },
            { Icon(Icons.Default.Menu, null) }
        )

        NavigationBarItem(
            1 == currentIndex,
            { currentIndex = 1; navigateTo(Destinations.NewsFriends) },
            { Icon(Icons.Default.Add, null) }
        )

        NavigationBarItem(
            2 == currentIndex,
            { currentIndex = 2; navigateTo(Destinations.Profile) },
            { Icon(Icons.Default.Close, null) }
        )
    }
}