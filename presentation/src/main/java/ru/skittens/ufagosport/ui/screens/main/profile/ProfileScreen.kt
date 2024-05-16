package ru.skittens.ufagosport.ui.screens.main.profile

import android.webkit.WebSettings.TextSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.koin.compose.koinInject
import ru.skittens.ufagosport.R
import ru.skittens.ufagosport.ui.elements.TitleLargeText
import ru.skittens.ufagosport.ui.elements.TitleMediumText
import ru.skittens.ufagosport.ui.elements.TitleSmallText
import ru.skittens.ufagosport.ui.navigation.NavigationFun

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel = koinInject()) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .background(Color.Black)
            .fillMaxSize()
            .verticalScroll(scrollState)
            .fillMaxHeight()
            .padding(vertical = 6.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row (Modifier.
            background(Color(0xFF74FF79),
                shape = RoundedCornerShape(size = 50.dp))
                .padding(6.dp, 8.dp, 16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically){
            Image(painterResource(R.drawable.crown), null,
                Modifier
                    .fillMaxSize(.1f)
                    .padding(top = 2.dp))
            TitleSmallText(text = "280xp", color = Color.Black)
        }
        Spacer(modifier = Modifier.weight(1.0f))
        Image(painterResource(R.drawable.person), null,
            Modifier
                .fillMaxSize(1.0f)
                .zIndex(6f))
        Spacer(Modifier.height(18.dp))
        TitleLargeText(
            "Василий Петров",
            Modifier.fillMaxWidth(.8f),
            TextAlign.Center,
            Color.White,

        )
        Row(
            Modifier
                .padding(top = 8.dp)
                .clickable {
                    //TODO nav to RatingScreen()
                }) {
            TitleMediumText(
                "Звание:",
                Modifier.fillMaxWidth(.2f),
                TextAlign.Center,
                Color.White
            )
            TitleMediumText(
                "Мышь",
                Modifier.fillMaxWidth(.2f),
                TextAlign.Center,
                Color(0xFF74FF79)
            )
            Image(painterResource(R.drawable.chevron_right), null,
                Modifier
                    .fillMaxSize(.1f)
                    .padding(top = 2.dp))
        }
        Spacer(modifier = Modifier.weight(1.0f))
        Row(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color(0xFF232323))
                .fillMaxSize()
                .fillMaxWidth()
                .padding(12.dp, 8.dp)
                .clickable {
                    //TODO nav to FriendsScreen()
                },
            verticalAlignment = Alignment.CenterVertically) {
            Box {
                Icon(Icons.Default.Groups, null, tint = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0x13FFFFFF))
                        .padding(all = 10.dp))
            }
            TitleMediumText(text = "Друзья", Modifier.padding(start = 12.dp))
            Spacer(modifier = Modifier.weight(1.0f))
            Image(painterResource(R.drawable.chevron_right), null,
                Modifier
                    .padding(top = 2.dp))
        }
        Spacer(Modifier.height(12.dp))
        Row(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color(0xFF232323))
                .fillMaxSize()
                .fillMaxWidth()
                .padding(12.dp, 8.dp)
                .clickable {
                    //TODO nav to AchievementScreen()
                },
            verticalAlignment = Alignment.CenterVertically) {
            Box {
                Icon(Icons.Default.EmojiEvents, null, tint = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0x13FFFFFF))
                        .padding(all = 10.dp))
            }
            TitleMediumText(text = "Мои достижения", Modifier.padding(start = 12.dp))
            Spacer(modifier = Modifier.weight(1.0f))
            Image(painterResource(R.drawable.chevron_right), null,
                Modifier
                    .padding(top = 2.dp))
        }
        Spacer(Modifier.height(12.dp))
        Row(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color(0xFF232323))
                .fillMaxSize()
                .fillMaxWidth()
                .padding(12.dp, 8.dp)
                .clickable {

                },
            verticalAlignment = Alignment.CenterVertically) {
            Box {
                Icon(Icons.Default.Assignment, null, tint = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0x13FFFFFF))
                        .padding(all = 10.dp))
            }
            TitleMediumText(text = "Мои мероприятия", Modifier.padding(start = 12.dp))
            Spacer(modifier = Modifier.weight(1.0f))
            Image(painterResource(R.drawable.chevron_right), null,
                Modifier
                    .padding(top = 2.dp))
        }
        Spacer(modifier = Modifier.weight(1.0f))
    }
}
