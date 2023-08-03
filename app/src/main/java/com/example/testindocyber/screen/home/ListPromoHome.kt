package com.example.testindocyber.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testindocyber.util.ViewModelListPromo
import com.example.testindocyber.util.itempromo.PromoItem

@Composable
fun ListPromoHome(navigate: (PromoItem) -> Unit): @Composable (PaddingValues) -> Unit  = {
    val promoViewModel = viewModel(modelClass = ViewModelListPromo::class.java)
    val promo by promoViewModel.promo.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = promo,
            itemContent = {
                ListPromoScreen(promo = it, navigateToDetail = navigate)
            }
        )
    }
}