package ru.skittens.ufagosport.ui.screens.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.skittens.ufagosport.R
import ru.skittens.ufagosport.ui.elements.TitleLargeText
import ru.skittens.ufagosport.ui.elements.TitleMediumText
import ru.skittens.ufagosport.ui.elements.TitleSmallText
import ru.skittens.ufagosport.ui.navigation.Destinations
import ru.skittens.ufagosport.ui.navigation.NavigationFun

@Composable
fun ProfileScreen(navigateTo: NavigationFun, profileViewModel: ProfileViewModel = koinInject()) {
    val user by profileViewModel.getUser().collectAsState()
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .background(Color.Black)
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 6.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            Modifier
                .fillMaxWidth(.35f)
                .aspectRatio(1f)) {
            Image(
                painterResource(profileViewModel.getAvatar(user.data).drawable), null,
                Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
            )
            Row(
                Modifier
                    .background(
                        Color(0xFF74FF79), shape = CircleShape
                    )
                    .padding(12.dp, 8.dp, 16.dp, 8.dp)
                    .align(Alignment.BottomCenter), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(R.drawable.crown),
                    null,
                )
                Spacer(modifier = Modifier.width(4.dp))
                TitleSmallText(text = "280xp", color = Color.Black)
            }
        }
        Spacer(Modifier.height(18.dp))
        TitleLargeText(
            user.data?.email.orEmpty(),
            Modifier.fillMaxWidth(.8f),
            TextAlign.Center,
            Color.White,
            )
        Row(
            Modifier
                .padding(top = 8.dp)
                .clickable {
                    navigateTo(Destinations.Rating)
                }) {
            TitleMediumText(
                "Звание:", Modifier.fillMaxWidth(.2f), TextAlign.Center, Color.White
            )
            TitleMediumText(
                "Мышь", Modifier.fillMaxWidth(.2f), TextAlign.Center, Color(0xFF74FF79)
            )
            Image(
                painterResource(R.drawable.chevron_right),
                null,
                Modifier
                    .fillMaxSize(.1f)
                    .padding(top = 2.dp)
            )
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
                    navigateTo(Destinations.ListFriends)
                }, verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Icon(
                    Icons.Default.Groups,
                    null,
                    tint = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0x13FFFFFF))
                        .padding(all = 10.dp)
                )
            }
            TitleMediumText(text = "Друзья", Modifier.padding(start = 12.dp))
            Spacer(modifier = Modifier.weight(1.0f))
            Image(
                painterResource(R.drawable.chevron_right), null, Modifier.padding(top = 2.dp)
            )
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
                    navigateTo(Destinations.Achievement)
                }, verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Icon(
                    Icons.Default.EmojiEvents,
                    null,
                    tint = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0x13FFFFFF))
                        .padding(all = 10.dp)
                )
            }
            TitleMediumText(text = "Мои достижения", Modifier.padding(start = 12.dp))
            Spacer(modifier = Modifier.weight(1.0f))
            Image(
                painterResource(R.drawable.chevron_right), null, Modifier.padding(top = 2.dp)
            )
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

                }, verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Icon(
                    Icons.AutoMirrored.Filled.Assignment,
                    null,
                    tint = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0x13FFFFFF))
                        .padding(all = 10.dp)
                )
            }
            TitleMediumText(text = "Мои мероприятия", Modifier.padding(start = 12.dp))
            Spacer(modifier = Modifier.weight(1.0f))
            Image(
                painterResource(R.drawable.chevron_right), null, Modifier.padding(top = 2.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1.0f))
    }
}

fun getRank(rating: Int) = when(rating){
    in 0..299 -> "Мышь"
    in 300..599 -> "Белка"
    in 600..899 -> "Кот"
    in 900..1199 -> "Лиса"
    in 1200..1499 -> "Волк"
    in 1500..1799 -> "Медведь"
    in 1800..2099 -> "Орел"
    in 2100..2399 -> "Ягуар"
    in 2400..2699 -> "Носорог"
    in 2700..2999 -> "Крокодил"
    else -> "Лев"
}