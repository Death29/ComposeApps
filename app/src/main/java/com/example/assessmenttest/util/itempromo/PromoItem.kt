package com.example.assessmenttest.util.itempromo

import android.os.Parcelable
import com.example.assessmenttest.model.response.Img
import com.example.assessmenttest.model.response.ResponsePromoItem
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PromoItem(
    val img: Img?,
    @field:SerializedName("nama")
    val name: String?,
    @field:SerializedName("count")
    val count: Int?,
    @field:SerializedName("desc")
    val desc: String?
): Parcelable
