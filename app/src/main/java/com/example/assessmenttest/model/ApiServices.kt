package com.example.assessmenttest.model

import com.example.assessmenttest.util.itempromo.PromoItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("promos")
    suspend fun getListPromo(): Response<List<PromoItem>>
}