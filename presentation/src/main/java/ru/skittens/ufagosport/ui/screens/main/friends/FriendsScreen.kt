package ru.skittens.ufagosport.ui.screens.main.friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFirstOrNull
import org.koin.compose.koinInject
import ru.skittens.domain.util.Resource
import ru.skittens.ufagosport.R
import ru.skittens.ufagosport.ui.elements.BodyLargeText
import ru.skittens.ufagosport.ui.elements.TitleLargeText
import ru.skittens.ufagosport.ui.elements.TitleSmallText
import ru.skittens.ufagosport.ui.screens.start.registration.Avatar

@Composable
fun FriendsScreen(friendsViewModel: FriendsViewModel = koinInject()) {
    val scrollState = rememberScrollState()
    val user by friendsViewModel.getUser().collectAsState(Resource.Loading())
    val friends by friendsViewModel.getFriends().collectAsState(listOf())
    LaunchedEffect(user) {
        user.data?.id?.let { friendsViewModel.loadFriends(it) }
    }
    if (friends?.isEmpty() == true) {
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
            Spacer(modifier = Modifier.weight(1.0f))
            Image(painterResource(R.drawable.empty_profile), null, Modifier.fillMaxSize(1.0f))
            Spacer(Modifier.height(18.dp))
            TitleLargeText(
                "Пока что ты не добавил ни одного друга... Давай же поскорее найдем единомышленников!",
                Modifier.fillMaxWidth(.8f),
                TextAlign.Center,
                Color.White
            )
            Spacer(modifier = Modifier.weight(1.0f))
            FilledTonalButton(
                {},
                Modifier.fillMaxWidth(.9f),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.filledTonalButtonColors(Color(0xFF74FF79))
            ) {
                Row(Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.PersonAdd, null, tint = Color.Black)
                    Spacer(Modifier.width(12.dp))
                    BodyLargeText("Добавить друга", color = Color.Black)
                }
            }
            Spacer(Modifier.height(20.dp))
        }
    } else {
        LazyColumn(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(friends.orEmpty().filterNotNull(), key = { it.id }) {
                Card(Modifier.fillMaxWidth()) {
                    Row(
                        Modifier.fillMaxWidth().padding(12.dp, 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painterResource(getAvatar(it.photo).drawable),
                            null,
                            Modifier.fillMaxWidth(.1f).aspectRatio(1f)
                        )

                        TitleSmallText(it.email, Modifier.padding(start = 12.dp))
                        Spacer(Modifier.weight(1f))
                        BodyLargeText(it.rating.elo + "xp")
                    }
                }
            }
        }
    }
}

fun getAvatar(avatarName: String) =
    Avatar.entries.toList().fastFirstOrNull { it.name == avatarName } ?: Avatar.First