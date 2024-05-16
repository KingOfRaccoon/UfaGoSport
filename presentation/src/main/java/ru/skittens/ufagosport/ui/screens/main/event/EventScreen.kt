package ru.skittens.ufagosport.ui.screens.main.event

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.BubbleChart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.skittens.domain.entity.Playground
import ru.skittens.ufagosport.R
import ru.skittens.ufagosport.ui.elements.BodyLargeText
import ru.skittens.ufagosport.ui.elements.BodyMediumText
import ru.skittens.ufagosport.ui.elements.PageIndicator
import ru.skittens.ufagosport.ui.elements.TitleLargeText
import ru.skittens.ufagosport.ui.elements.TitleMediumText
import ru.skittens.ufagosport.ui.elements.TitleSmallText
import ru.skittens.ufagosport.ui.navigation.Destinations
import ru.skittens.ufagosport.ui.screens.main.MainTopBar
import ru.skittens.ufagosport.ui.screens.start.registration.Avatar
import ru.skittens.ufagosport.ui.screens.start.registration.ItemAvatar

@Composable
fun EventScreen() {
    //TODO Пофиксить отображение. Добавить стрелочку назад?
    val context = LocalContext.current
    Column {
        Row {
            Spacer(Modifier.weight(1f))
            Image(
                painterResource(R.drawable.ball),
                null,
            )
            Spacer(Modifier.weight(1f))
        }
        Row(Modifier.padding(top = 12.dp)) {
            Spacer(Modifier.weight(1f))
            TitleLargeText(text = "Дружеский матч. Баскетбол")
            Spacer(Modifier.weight(1f))
        }


        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 16.dp)) {
            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.People,
                        null,
                        Modifier
                            .size(20.dp)
                            .padding(end = 4.dp),
                        Color.White.copy(.5f)
                    )
                    BodyLargeText("Игроков: 12")
                }
            }
            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationCity,
                        null,
                        Modifier
                            .size(20.dp)
                            .padding(end = 4.dp),
                        Color.White.copy(.5f)
                    )
                    BodyLargeText("type")
//                    BodyLargeText("${playground.classPlayground?.name}, ${playground.typePlayground?.name}")
                }
            }

            item {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        null,
                        Modifier
                            .size(20.dp)
                            .padding(end = 4.dp),
                        Color.White.copy(.5f)
                    )

                    BodyLargeText("playground.address", Modifier.weight(1f))
//                    BodyLargeText(playground.address, Modifier.weight(1f))

                    TextButton(
                        {

                            val intent = Intent(
                                Intent.ACTION_VIEW,
//                                Uri.parse("https://2gis.ru/geo/${playground.longitude},${playground.latitude}")
                            )
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.textButtonColors(Color.Transparent)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TitleMediumText("На карте", color = Color(0xFF74FF79))
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowForwardIos,
                                null,
                                Modifier
                                    .padding(start = 4.dp)
                                    .size(20.dp),
                                Color(0xFF74FF79)
                            )
                        }
                    }
                }
            }

            item {
                TitleSmallText("Воркаутеры и участники программы SOTKA в очередной раз соберутся для тренировки, общения и обмена опытом. Если у кого есть желание присоединиться, чтобы потренироваться вместе с нами в дружеской атмосфере старого доброго уличного воркаута, будем рады всем)",
                    color = Color.White)
            }

            item {
                Spacer(Modifier.height(12.dp))

                BodyLargeText("Организатор", color = Color.White.copy(.5f))

                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(R.drawable.avatar_10),
                        null,
                        Modifier
                            .height(40.dp)
                            .width(40.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    BodyLargeText("Семен Семенов", color = Color.White)
                }
            }

            item {
                Spacer(Modifier.height(12.dp))

                Row {
                    BodyLargeText("Участники", color = Color.White.copy(.5f))
                    Spacer(Modifier.weight(1f))
                    BodyLargeText("3 из 12", color = Color(0xFF74FF79))
                }

                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(R.drawable.avatar_10),
                        null,
                        Modifier
                            .height(40.dp)
                            .width(40.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.width(8.dp))
                    Image(
                        painterResource(R.drawable.avatar_9),
                        null,
                        Modifier
                            .height(40.dp)
                            .width(40.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.width(8.dp))
                    Image(
                        painterResource(R.drawable.avatar_5),
                        null,
                        Modifier
                            .height(40.dp)
                            .width(40.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            item {
                Spacer(Modifier.height(24.dp))

                FilledTonalButton(
                    {},
                    Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(Color(0xFF74FF79))
                ) {
                    Row(
                        Modifier.padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.FlashOn,
                            null,
                            Modifier.padding(end = 8.dp),
                            Color.Black
                        )
                        BodyLargeText("Присоединиться", color = Color.Black)
                    }
                }
                Spacer(Modifier.height(12.dp))
                FilledTonalButton(
                    {},
                    Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(Color(0xFF232323))
                ) {
                    Row(
                        Modifier.padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.People,
                            null,
                            Modifier.padding(end = 8.dp),
                            Color.White
                        )
                        BodyLargeText("Позвать друга", color = Color.White)
                    }
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}