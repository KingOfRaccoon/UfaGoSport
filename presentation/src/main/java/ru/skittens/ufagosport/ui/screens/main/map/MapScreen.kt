package ru.skittens.ufagosport.ui.screens.main.map

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LifecycleStartEffect
import coil.compose.AsyncImage
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import org.koin.compose.koinInject
import ru.skittens.domain.entity.Playground
import ru.skittens.ufagosport.R
import ru.skittens.ufagosport.ui.elements.BodyLargeText
import ru.skittens.ufagosport.ui.elements.BodyMediumText
import ru.skittens.ufagosport.ui.elements.PageIndicator
import ru.skittens.ufagosport.ui.elements.TitleLargeText
import ru.skittens.ufagosport.ui.elements.TitleMediumText
import ru.skittens.ufagosport.ui.elements.TitleSmallText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(mapViewModel: MapViewModel = koinInject()) {
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current
    val playgroundState by mapViewModel.selectedPlaygroundFlow.collectAsState()
    val sheetState = rememberModalBottomSheetState()

    val mapView = remember {
        MapView(context)
    }

    LaunchedEffect(Unit) {
        MapKitFactory.initialize(context)
        mapView.mapWindow.map.move(
            CameraPosition(
                Point(59.935493, 30.327392),
                /* zoom = */ 17.0f,
                /* azimuth = */ 150.0f,
                /* tilt = */ 30.0f
            )
        )


        mapView.mapWindow.map.mapObjects.addPlacemark {
            it.geometry = Point(59.935493, 30.327392)
            it.setIcon(ImageProvider.fromResource(context, R.drawable.ic_dollar_pin))
        }
    }

    LifecycleStartEffect(Unit, lifecycle) {
        MapKitFactory.getInstance().onStart()
        mapView.onStart()

        onStopOrDispose {
            mapView.onStop()
            MapKitFactory.getInstance().onStop()
        }
    }

    if (playgroundState != null)
        PlaygroundDialog(sheetState, playgroundState!!, {})

    AndroidView(modifier = Modifier.fillMaxSize(), factory = { mapView })
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PlaygroundDialog(sheetState: SheetState, playground: Playground, onDismess: () -> Unit) {
    val carouselPager = rememberPagerState { 3 }
    ModalBottomSheet(
        onDismess,
        Modifier.fillMaxWidth(),
        sheetState,
        dragHandle = null,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column {
            Box {
                HorizontalPager(carouselPager) {
                    AsyncImage(
                        "https://workout.su/uploads/userfiles/IMG_20140405_170531.jpg",
                        null,
                        Modifier.fillMaxWidth().aspectRatio(2f),
                        contentScale = ContentScale.Crop
                    )
                }

                IconButton(
                    {},
                    Modifier.align(Alignment.TopStart).padding(16.dp),
                    colors = IconButtonDefaults.iconButtonColors(Color.White.copy(.30f))
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                }

                IconButton(
                    {},
                    Modifier.align(Alignment.TopEnd).padding(16.dp),
                    colors = IconButtonDefaults.iconButtonColors(Color.White.copy(.30f))
                ) {
                    Icon(Icons.Filled.Favorite, null)
                }

                PageIndicator(
                    carouselPager.pageCount,
                    Modifier.align(Alignment.BottomCenter).padding(bottom = 12.dp),
                    carouselPager.currentPage
                )
            }

            Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(top = 16.dp)) {
                Row(
                    Modifier.fillMaxWidth(),
                    Arrangement.spacedBy(8.dp)
                ) {
                    Column(Modifier.weight(2f)) {
                        TitleLargeText("Площадка №1345", color = Color.White)
                        Row(
                            Modifier.padding(top = 6.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.TripOrigin,
                                null,
                                Modifier.size(20.dp),
                                Color.White.copy(.5f)
                            )

                            BodyLargeText("Баскетбольная площадка")
                        }
                    }

                    Card(
                        {},
                        Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(Color.White.copy(.1f)),
                    ) {
                        Column(
                            Modifier.padding(vertical = 6.dp)
                                .align(Alignment.CenterHorizontally),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TitleMediumText("9.4", color = Color.White)
                                Icon(Icons.Default.Star, null, tint = Color(0xFF74FF79))
                            }

                            BodyMediumText(
                                "15 отзывов",
                                color = Color.White.copy(.5f)
                            )
                        }
                    }
                }

                Row(
                    Modifier.fillMaxWidth().padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationCity,
                        null,
                        Modifier.size(20.dp).padding(end = 4.dp),
                        Color.White.copy(.5f)
                    )

                    BodyLargeText("Средняя, легендарная")
                }

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        null,
                        Modifier.size(20.dp).padding(end = 4.dp),
                        Color.White.copy(.5f)
                    )

                    BodyLargeText("Кочновский проезд 4 к3")

                    Spacer(Modifier.weight(1f))

                    TextButton(
                        {},
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
                                Modifier.padding(start = 4.dp).size(20.dp),
                                Color(0xFF74FF79)
                            )
                        }
                    }
                }


                Row(Modifier.fillMaxWidth(), Arrangement.spacedBy(12.dp)) {
                    Card(
                        {},
                        Modifier.weight(1f).aspectRatio(2f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(Color.White.copy(.1f)),
                    ) {
                        Column(
                            Modifier.fillMaxHeight().padding(vertical = 6.dp)
                                .align(Alignment.CenterHorizontally),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painterResource(R.drawable.ic_dumbell),
                                null,
                                tint = Color(0xFF74FF79)
                            )

                            TitleSmallText("Оборудование", color = Color.White)
                        }
                    }

                    Card(
                        {},
                        Modifier.weight(1f).aspectRatio(2f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(Color.White.copy(.1f)),
                    ) {
                        Column(
                            Modifier.fillMaxHeight().padding(vertical = 6.dp)
                                .align(Alignment.CenterHorizontally),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(Icons.Default.Accessibility, null, tint = Color(0xFF74FF79))

                            TitleSmallText("Тренировки", color = Color.White)
                        }
                    }
                }

                Card(
                    {},
                    Modifier.fillMaxWidth().padding(top = 12.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(Color(0xFF74FF79))
                ) {
                    Row(
                        Modifier.fillMaxWidth().padding(12.dp, 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(Modifier.clip(CircleShape).background(Color.Black.copy(.1f))) {
                            Icon(
                                Icons.Default.SportsBasketball,
                                null,
                                Modifier.padding(10.dp).rotate(-45f),
                                Color.Black
                            )
                        }


                        Spacer(Modifier.width(12.dp))

                        Column(verticalArrangement = Arrangement.Center) {
                            TitleSmallText("Точка интереса", color = Color.Black)
                            BodyMediumText("До начала: 32 мин", color = Color.Black.copy(.5f))
                        }

                        Spacer(Modifier.weight(1f))

                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForwardIos,
                            null,
                            Modifier.padding(vertical = 5.dp),
                            Color.Black
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                BodyLargeText("Реклама", color = Color.White.copy(.5f))

                Spacer(Modifier.height(8.dp))

                Card(
                    {},
                    Modifier.fillMaxWidth().aspectRatio(4f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(Color.White.copy(.1f)),
                ) {
                    Column(
                        Modifier.fillMaxHeight().padding(vertical = 6.dp)
                            .align(Alignment.CenterHorizontally),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TitleSmallText("Тут может быть ваша реклама", color = Color.White)
                    }
                }

                Spacer(Modifier.height(12.dp))

                BodyLargeText("Мероприятия", color = Color.White.copy(.5f))

                Spacer(Modifier.height(8.dp))

                Card(
                    {},
                    Modifier.fillMaxWidth().padding(top = 12.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(Color.White.copy(.1f))
                ) {
                    Row(
                        Modifier.fillMaxWidth().padding(12.dp, 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(Modifier.clip(CircleShape).background(Color.Black.copy(.1f))) {
                            Icon(
                                painterResource(R.drawable.ic_trophy),
                                null,
                                Modifier.padding(10.dp),
                                Color.White
                            )
                        }

                        Spacer(Modifier.width(12.dp))

                        Column(verticalArrangement = Arrangement.Center) {
                            TitleSmallText("Точка интереса", color = Color.White)
                            BodyMediumText("До начала: 32 мин", color = Color.White)
                        }

                        Spacer(Modifier.weight(1f))

                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForwardIos,
                            null,
                            Modifier.padding(vertical = 5.dp),
                            Color.White
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

                FilledTonalButton({}, Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.filledTonalButtonColors(Color(0xFF74FF79))) {
                    Row(Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.FlashOn, null, Modifier.padding(end = 8.dp), Color.Black)
                        BodyLargeText("Бросить вызов", color = Color.Black)
                    }
                }

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}