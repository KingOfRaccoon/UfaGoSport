package ru.skittens.ufagosport.ui.screens.main.news

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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

@Composable
fun NewsScreen(newsViewModel: NewsViewModel = koinInject()) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .fillMaxHeight()
            .padding(vertical = 6.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(R.drawable.empty_news), null, Modifier.fillMaxSize(1.0f))
        Spacer(Modifier.height(18.dp))
        TitleLargeText(
            "Чтобы получать новости, добавляй друзей и начинайте вместе тренироваться",
            Modifier.fillMaxWidth(.8f),
            TextAlign.Center,
            Color.White
        )
        Spacer(Modifier.height(18.dp))
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
    }