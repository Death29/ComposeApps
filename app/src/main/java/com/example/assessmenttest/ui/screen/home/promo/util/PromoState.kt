package com.example.assessmenttest.ui.screen.home.promo.util

import com.example.assessmenttest.model.response.ResponseListPromo
import com.example.assessmenttest.model.response.ResponseListPromoItem
import com.example.assessmenttest.util.itempromo.PromoItem
import com.example.testindocyber.model.network.room.Payment

data class PromoState(
    val promo: List<PromoItem>? = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

data class HistoryState(
    val payment: List<Payment>? = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)