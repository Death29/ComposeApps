package com.example.testindocyber.screen.home

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.testindocyber.util.itempromo.PromoItem


@Composable
fun ListPromoScreen(promo: PromoItem, navigateToDetail: (PromoItem) -> Unit) {

    Card(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(Modifier.clickable { navigateToDetail(promo) }) {
            PromoImage(promo)
        }
        Column(
            modifier = Modifier.padding(top =  3.dp, bottom = 3.dp, start = 10.dp, end = 10.dp)
        ) {
            Text(text = promo.name.toString(), style = typography.labelLarge)
        }
    }
}


@Composable
fun PromoImage(promo: PromoItem) {

    Image(
        painter = rememberImagePainter(data = promo.img?.url),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .padding(top = 0.dp, bottom = 0.dp, start = 10.dp, end = 10.dp)
            .size(350.dp, 200.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
    )
}


