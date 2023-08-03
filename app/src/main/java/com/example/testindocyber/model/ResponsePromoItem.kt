package com.example.testindocyber.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponsePromoItem(

	@field:SerializedName("img")
	val img: Img? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null,

) : Parcelable

@Parcelize
data class Img(
	@field:SerializedName("url")
	val url: String? = null,
	@field:SerializedName("size")
	val size: Double? = null

) : Parcelable

