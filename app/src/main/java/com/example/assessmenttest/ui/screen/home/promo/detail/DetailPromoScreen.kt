package com.example.assessmenttest.ui.screen.home.promo.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.assessmenttes.R
import com.example.assessmenttest.util.itempromo.PromoItem

@Composable
fun DetailPromoScreen(promo: PromoItem){
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    BannerPromo(
                        promo,
                        this@BoxWithConstraints.maxHeight
                    )
                    PromoContent(promo, this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
}

@Composable
fun PromoContent(promo: PromoItem, boxWithConstraintsScope: Dp) {
    Column {
        Row {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                Name(promo = promo)
            }

            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Text(text = promo.count?.let { stringResource(id = R.string.count_coupons, it) } ?: "", modifier = Modifier.padding(top = 10.dp, bottom = 0.dp, start = 10.dp, end = 10.dp), style = MaterialTheme.typography.labelLarge )
            }
        }
        Column(
            modifier = Modifier.padding(top =  10.dp, bottom = 3.dp, start = 10.dp, end = 10.dp)
        ) {
            Text(text = promo.desc.toString(), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun Name(promo: PromoItem) {
    Text(
        text = promo.name.toString(),
        modifier = Modifier.padding(top = 10.dp, bottom = 0.dp, start = 10.dp, end = 10.dp),
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BannerPromo(promo: PromoItem, maxHeight: Dp) {
    Image(
        modifier = Modifier
            .heightIn(max = maxHeight / 2)
            .fillMaxWidth()
            .padding(top = 0.dp, bottom = 0.dp, start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp))),
        painter = rememberImagePainter(data = promo.img?.url),
        contentScale = ContentScale.FillWidth,
        contentDescription = null
    )
}