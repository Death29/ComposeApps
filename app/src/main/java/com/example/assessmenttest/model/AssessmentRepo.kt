package com.example.assessmenttest.model

import com.example.assessmenttest.model.response.ResponseListPromo
import com.example.assessmenttest.model.response.ResponseListPromoItem
import com.example.assessmenttest.model.response.StateResponse
import com.example.assessmenttest.util.itempromo.PromoItem
import kotlinx.coroutines.flow.Flow

interface AssessmentRepo {
    fun getListPromo():Flow<StateResponse<List<PromoItem>>>
}