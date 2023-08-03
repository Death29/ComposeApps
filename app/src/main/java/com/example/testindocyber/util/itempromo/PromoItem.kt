package com.example.testindocyber.util.itempromo

import android.os.Parcelable
import com.example.testindocyber.model.Img
import com.example.testindocyber.model.ResponsePromoItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class PromoItem(
    val img: Img?,
    val name: String?,
    val count: Int?,
    val desc: String?
): Parcelable

fun ResponsePromoItem.toPromoItem() = PromoItem(img, nama, count, desc)
