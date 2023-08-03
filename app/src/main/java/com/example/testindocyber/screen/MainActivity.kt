package com.example.testindocyber.screen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.testindocyber.screen.detail.DetailPromo
import com.example.testindocyber.screen.home.ListPromoHome
import com.example.testindocyber.ui.theme.TestIndocyberTheme
import com.example.testindocyber.util.itempromo.PromoItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestIndocyberTheme {
                MyApps{
                    startActivity(DetailPromo.newIntent(this, it))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApps(navigateToDetail: (PromoItem) -> Unit) {
    Scaffold(
        content = ListPromoHome(navigate = navigateToDetail)
    )
}


