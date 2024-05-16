package ru.skittens.ufagosport.ui.screens.start.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.skittens.ufagosport.R
import ru.skittens.ufagosport.ui.elements.BodyLargeText
import ru.skittens.ufagosport.ui.elements.TitleLargeText
import ru.skittens.ufagosport.ui.navigation.Destinations
import ru.skittens.ufagosport.ui.navigation.NavigationFun

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    navigateTo: NavigationFun,
    authenticationViewModel: AuthenticationViewModel = koinInject()
) {
    val loginState by authenticationViewModel.loginFlow.collectAsState()
    val passwordState by authenticationViewModel.passwordFlow.collectAsState()
    val navigateToMain by authenticationViewModel.navigateToMain.collectAsState()

    Scaffold(
        Modifier.fillMaxSize(),
        topBar = { TopAppBar({}, colors = TopAppBarDefaults.topAppBarColors(Color.Black)) }) {
        Column(
            Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painterResource(R.drawable.big_logo), null, Modifier.fillMaxSize(0.4f))

            TitleLargeText(
                "Будь частью спортивного сообщества соревнуйся, совершенствуйся, общайся",
                Modifier.fillMaxWidth(.8f),
                TextAlign.Center,
                Color.White
            )

            Spacer(Modifier.height(48.dp))
            LoginTextField(loginState, authenticationViewModel::updateLogin)
            Spacer(Modifier.height(18.dp))
            PasswordTextField(passwordState, authenticationViewModel::updatePassword)
            Spacer(Modifier.weight(1f))
            FilledTonalButton(
                authenticationViewModel::loginUser,
                Modifier.fillMaxWidth(.9f),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.filledTonalButtonColors(Color(0xFF74FF79))
            ) {
                Row(Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
                    BodyLargeText("Далее", color = Color.Black)
                }
            }
            Spacer(Modifier.height(20.dp))
        }
    }

    LaunchedEffect(navigateToMain){
        if (navigateToMain == null) return@LaunchedEffect

        if (navigateToMain == true)
            navigateTo(Destinations.Main)
        else
            navigateTo(Destinations.Registration)

        authenticationViewModel.clearNavigate()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(password: String, updatePassword: (String) -> Unit) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    TextField(
        modifier = Modifier.fillMaxWidth(.9f),
        colors = textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        value = password,
        onValueChange = updatePassword,
        singleLine = true,
        placeholder = { Text("Пароль") },
        visualTransformation =
        if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                // Please provide localized description for accessibility services
                val description = if (passwordHidden) "Show password" else "Hide password"
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(login: String, updateLogin: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(.9f),
        colors = textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp),
        value = login,
        onValueChange = updateLogin,
        singleLine = true,
        placeholder = { Text("Логин") },
        visualTransformation = VisualTransformation.None,
    )
}
