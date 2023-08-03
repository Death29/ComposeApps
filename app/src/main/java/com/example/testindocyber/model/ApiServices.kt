package com.example.testindocyber.model

import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("promos")
    suspend fun getListPromo(): Response<List<ResponsePromoItem>>
}