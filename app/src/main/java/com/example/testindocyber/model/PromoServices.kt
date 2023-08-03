package com.example.testindocyber.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PromoServices @Inject constructor(private val apiService: ApiServices) {
    suspend fun getListPromo(): List<ResponsePromoItem>{
        return withContext(Dispatchers.IO){
            val promo = apiService.getListPromo()
            promo.body() ?: emptyList()
        }
    }
}