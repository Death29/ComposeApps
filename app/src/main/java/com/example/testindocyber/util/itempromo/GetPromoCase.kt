package com.example.testindocyber.util.itempromo

import com.example.testindocyber.util.Repository
import javax.inject.Inject

class GetPromoCase @Inject constructor(private val promoRepo: Repository ) {
    suspend operator fun invoke():List<PromoItem>{
        return promoRepo.getPromoList().shuffled()
    }
}