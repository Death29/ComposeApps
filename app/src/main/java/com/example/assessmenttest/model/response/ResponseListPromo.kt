package com.example.assessmenttest.model.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.assessmenttest.util.itempromo.PromoItem
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseListPromo(

	@field:SerializedName("ResponseListPromo")
	val responseListPromo: List<PromoItem>
) : Parcelable

@Parcelize
data class Medium(

	@field:SerializedName("ext")
	val ext: String? = null,

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("size")
	val size: Double? = null,

	@field:SerializedName("mime")
	val mime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("hash")
	val hash: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
) : Parcelable

@Parcelize
data class Thumbnail(

	@field:SerializedName("ext")
	val ext: String? = null,

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("size")
	val size: Double? = null,

	@field:SerializedName("mime")
	val mime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("hash")
	val hash: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
) : Parcelable

@Parcelize
data class Formats(

	@field:SerializedName("small")
	val small: Small? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("medium")
	val medium: Medium? = null,

	@field:SerializedName("large")
	val large: Large? = null
) : Parcelable

@Parcelize
data class Small(

	@field:SerializedName("ext")
	val ext: String? = null,

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("size")
	val size: Double? = null,

	@field:SerializedName("mime")
	val mime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("hash")
	val hash: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
) : Parcelable

@Parcelize
data class ResponseListPromoItem(

	@field:SerializedName("img")
	val img: Img? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("alt")
	val alt: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("Title")
	val title: String? = null,

	@field:SerializedName("created_at")
	val created_at: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("name_promo")
	val namePromo: String? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("published_at")
	val publishedAt: String? = null,

	@field:SerializedName("desc_promo")
	val descPromo: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
) : Parcelable

@Parcelize
data class Large(

	@field:SerializedName("ext")
	val ext: String? = null,

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("size")
	val size: Double? = null,

	@field:SerializedName("mime")
	val mime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("hash")
	val hash: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
) : Parcelable
