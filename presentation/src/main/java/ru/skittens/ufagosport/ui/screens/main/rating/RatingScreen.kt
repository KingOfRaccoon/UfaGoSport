package ru.skittens.ufagosport.ui.screens.main.rating

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.skittens.ufagosport.R
import ru.skittens.ufagosport.ui.elements.BodyLargeText
import ru.skittens.ufagosport.ui.elements.TitleLargeText

@Composable
fun RatingScreen(ratingViewModel: RatingViewModel = koinInject()) {
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
}