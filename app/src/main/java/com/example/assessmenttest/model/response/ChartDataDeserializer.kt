package com.example.assessmenttest.model.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class ChartDataDeserializer: JsonDeserializer<DataChart> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): DataChart {
        val jsonObject = json.asJsonObject

        val type = jsonObject.get("type").asString
        return when(type){
            "donutChart"->context.deserialize<DataChart.DonutChartData>(json, DataChart.DonutChartData::class.java)
            "lineChart"->context.deserialize<DataChart.LineChartData>(json, DataChart.LineChartData::class.java)
            else -> throw JsonParseException("Unsupported Chart Type: $type")
        }
    }
}