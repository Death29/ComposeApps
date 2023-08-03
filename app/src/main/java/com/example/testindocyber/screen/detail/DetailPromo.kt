package com.example.testindocyber.screen.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.IntentCompat
import com.example.testindocyber.model.ResponsePromoItem
import com.example.testindocyber.screen.detail.ui.theme.TestIndocyberTheme
import com.example.testindocyber.util.itempromo.PromoItem

class DetailPromo : ComponentActivity() {

    private val promo : PromoItem by lazy {
        IntentCompat.getParcelableExtra(intent, DATA_PROMO, PromoItem::class.java) as PromoItem
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestIndocyberTheme {
                DetailPromoScreen(promo = promo)
            }
        }
    }

    companion object{
        private const val DATA_PROMO = "DATA"
        fun newIntent(context: Context, promoItem: PromoItem) =
            Intent(context, DetailPromo::class.java).apply {
                putExtra(DATA_PROMO, promoItem)
            }
    }
}
