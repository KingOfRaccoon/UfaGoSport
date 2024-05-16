package ru.skittens.ufagosport.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MarkChatUnread
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.util.fastFirstOrNull
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.skittens.ufagosport.ui.elements.DisplayLargeText
import ru.skittens.ufagosport.ui.elements.HeadlineLargeText
import ru.skittens.ufagosport.ui.elements.TitleLogo
import ru.skittens.ufagosport.ui.elements.TitleMediumText
import ru.skittens.ufagosport.ui.navigation.Destinations
import ru.skittens.ufagosport.ui.navigation.NavigationFun
import ru.skittens.ufagosport.ui.navigation.composable
import ru.skittens.ufagosport.ui.navigation.navigate
import ru.skittens.ufagosport.ui.screens.main.map.MapScreen
import ru.skittens.ufagosport.ui.screens.main.news.NewsScreen
import ru.skittens.ufagosport.ui.screens.main.profile.ProfileScreen

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
        NavHost(navHostController, Destinations.Map.name,
            Modifier
                .fillMaxSize()
                .padding(it)) {
            composable(Destinations.Map) {
                MapScreen()
            }

            composable(Destinations.NewsFriends) {
                NewsScreen()
            }

            composable(Destinations.Profile) {
                ProfileScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String) {
    TopAppBar({ TitleLogo(title) },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF000000)))
}

@Composable
fun MainBottomNavigationMenu(navigateTo: NavigationFun) {
    var currentIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(
        Modifier.fillMaxWidth(),
        containerColor = Color(0xFF000000)) {
        NavigationBarItem(
            0 == currentIndex,
            { currentIndex = 0; navigateTo(Destinations.Map) },
            icon = { Icon(Icons.Default.Map, null) },
            label = { Text(text = "Карта")},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF74FF79),
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.White,
                indicatorColor = Color(0xFF232323)
            )
        )

        NavigationBarItem(
            1 == currentIndex,
            { currentIndex = 1; navigateTo(Destinations.NewsFriends) },
            icon = { Icon(Icons.Default.MarkChatUnread, null) },
            label = { Text(text = "Новости")},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF74FF79),
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.White,
                indicatorColor = Color(0xFF232323)
            )
        )

        NavigationBarItem(
            2 == currentIndex,
            { currentIndex = 2; navigateTo(Destinations.Profile) },
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text(text = "Профиль")},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF74FF79),
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.White,
                indicatorColor = Color(0xFF232323)
            )
        )
    }

}