package ru.skittens.ufagosport.ui.screens.start.registration

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.skittens.domain.util.Resource
import ru.skittens.ufagosport.R
import ru.skittens.ufagosport.ui.elements.BodyLargeText
import ru.skittens.ufagosport.ui.elements.TitleLargeText
import ru.skittens.ufagosport.ui.navigation.Destinations
import ru.skittens.ufagosport.ui.navigation.NavigationFun
import ru.skittens.ufagosport.ui.screens.start.auth.AuthenticationViewModel

@Composable
fun RegistrationScreen(
    navigateTo: NavigationFun,
    authenticationViewModel: AuthenticationViewModel = koinInject()
) {
    val imageUser by authenticationViewModel.userImageFlow.collectAsState()
    val name by authenticationViewModel.nameFlow.collectAsState()
    val scrollState = rememberScrollState()
    val user by authenticationViewModel.getUser().collectAsState()

    Scaffold(
        Modifier.fillMaxSize().systemBarsPadding(),
        topBar = {
            Row(
                Modifier.fillMaxWidth().padding(vertical = 15.dp),
                Arrangement.Center,
                Alignment.CenterVertically
            ) { Image(painterResource(R.drawable.top_logo), null) }
        }
    ) {
        Column(
            Modifier.fillMaxSize().padding(it).padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleLargeText(
                "Добро пожаловать!\n" +
                        "Выбери себе аватар и придумай никнейм",
                Modifier.fillMaxWidth().padding(bottom = 24.dp),
                TextAlign.Center,
                Color.White
            )

            Box(Modifier.padding(bottom = 24.dp)) {
                Box(
                    Modifier.fillMaxWidth(0.4f).aspectRatio(1f).clip(CircleShape)
                        .border(4.dp, Color.White, CircleShape).padding(8.dp)
                ) {
                    AnimatedContent(imageUser) {
                        Image(
                            painterResource(it.drawable),
                            null,
                            Modifier.fillMaxSize().clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            LazyVerticalGrid(
                GridCells.Fixed(5),
                Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(22.dp)
            ) {
                items(Avatar.entries.toTypedArray(), { it.ordinal }) {
                    ItemAvatar(it.drawable, it == imageUser) {
                        authenticationViewModel.setUserImage(it)
                    }
                }
            }
            Spacer(Modifier.height(39.dp))
            NameTextField(name, authenticationViewModel::updateName)
            Spacer(Modifier.weight(1f))
            FilledTonalButton(
                authenticationViewModel::registrationUser,
                Modifier.fillMaxWidth(.9f),
                enabled = name.isNotEmpty(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.filledTonalButtonColors(Color(0xFF74FF79))
            ) {
                Row(Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
                    BodyLargeText("Создать аккаунт", color = Color.Black)
                }
            }
            Spacer(Modifier.height(20.dp))
        }
    }

    LaunchedEffect(user) {
        if (user is Resource.Success)
            navigateTo(Destinations.Main)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameTextField(name: String, updateName: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(.9f),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        value = name,
        onValueChange = updateName,
        singleLine = true,
        placeholder = { Text("Логин") },
        visualTransformation = VisualTransformation.None,
    )
}

@Composable
fun ItemAvatar(@DrawableRes drawable: Int, isCurrent: Boolean, updateChoice: () -> Unit) {
    Image(
        painterResource(drawable),
        null,
        Modifier.fillMaxWidth().aspectRatio(1f).clip(CircleShape).clickable {
            updateChoice()
        },
        contentScale = ContentScale.Crop
    )

    AnimatedVisibility(isCurrent, enter = enterAnimation, exit = exitAnimation) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color.Black.copy(0.6f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painterResource(R.drawable.check_circle),
                null,
                Modifier.padding(16.dp).fillMaxWidth().aspectRatio(1f),
                Color(0xFF74FF79)
            )
        }
    }
}

val enterAnimation = fadeIn()
val exitAnimation = fadeOut()