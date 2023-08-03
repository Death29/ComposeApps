package com.example.testindocyber.util

import com.example.testindocyber.model.PromoServices
import com.example.testindocyber.model.ResponsePromoItem
import com.example.testindocyber.util.itempromo.PromoItem
import com.example.testindocyber.util.itempromo.toPromoItem
import javax.inject.Inject

class Repository @Inject constructor(private val promoServices: PromoServices) {

    suspend fun getPromoList(): List<PromoItem>{
        return promoServices.getListPromo().map {
            it.toPromoItem()
        }
    }
}