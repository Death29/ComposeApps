package com.example.assessmenttest.model.network

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

sealed class DataChart{
	@Parcelize
	data class DonutChartData(
		@field:SerializedName("data")
		val data: List<DataDonutChart>?
	): DataChart(), Parcelable

	@Parcelize
	data class LineChartData(
		@field:SerializedName("data")
		val data: DataLineChart
	): DataChart(), Parcelable
}

@Parcelize
data class DataDonutChart(
	@field:SerializedName("label")
	var label: String = "",

	@field:SerializedName("percentage")
	var percentage: String= "",

	@field:SerializedName("data")
	var listDataDonut: List<DetailDataDonut> = arrayListOf()
):Parcelable

@Parcelize
data class DetailDataDonut(
	@field:SerializedName("trx_date")
	var trxDate: String,

	@field:SerializedName("nominal")
	var nominal: Long
):Parcelable

@Parcelize
data class DataLineChart(
	@field:SerializedName("month")
	val month: List<Int>?
):Parcelable