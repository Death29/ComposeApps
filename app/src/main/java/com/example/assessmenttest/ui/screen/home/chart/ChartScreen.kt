package com.example.assessmenttest.ui.screen.home.chart

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.assessmenttes.R
import com.example.assessmenttest.model.network.ChartDataDeserializer
import com.example.assessmenttest.model.network.DataChart
import com.example.assessmenttest.model.network.DataDonutChart
import com.example.assessmenttest.ui.screen.components.TopBarComponent
import com.google.gson.GsonBuilder
import kotlin.random.Random

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChartScreen(
    onDetailChart: (DataDonutChart) -> Unit
) {
    val context = LocalContext.current

    //Convert Data Chart from file and assign to variable
    val dataList = remember { mutableStateOf<List<DataChart>?>(null) }
    LaunchedEffect(Unit) {
        val jsonString = context.resources.openRawResource(R.raw.jsonformatter)
            .bufferedReader()
            .use { it.readText() }

        val gson = GsonBuilder()
            .registerTypeAdapter(DataChart::class.java, ChartDataDeserializer())
            .create()

        val data = gson.fromJson(jsonString, Array<DataChart>::class.java).toList()
        dataList.value = data
    }

    Scaffold(
        topBar = { TopBarComponent(title = stringResource(id = R.string.lbl_chart)) }
    ) { paddingValues ->
        if (dataList.value?.isEmpty() == true) Log.d("dataChart", "empty")
        else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                dataList.value?.let { list ->
                    for (dataChart in list) {
                        when (dataChart) {
                            is DataChart.DonutChartData -> {
                                //Data Donut Chart
                                val entries = ArrayList<PieChartData.Slice>()


                                dataChart.data?.forEach {

                                    val red = Random.nextFloat()
                                    val blue = Random.nextFloat()
                                    val green = Random.nextFloat()
                                    val randomColors = Color(red, green, blue, 1f)

                                    entries.add(
                                        PieChartData.Slice(
                                            it.label,
                                            it.percentage.toFloat(),
                                            randomColors
                                        )
                                    )
                                }

                                val donutData = PieChartData(slices = entries, PlotType.Pie)

                                Column(
                                    modifier = Modifier.wrapContentSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.height(20.dp))

                                    Text(
                                        text = stringResource(id = R.string.lbl_chart_donut),
                                        fontSize = 18.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Box(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .width(200.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        DonutPieChart(modifier = Modifier.wrapContentSize(),
                                            pieChartData = donutData,
                                            pieChartConfig = PieChartConfig(
                                                sliceLabelTextColor = Color.Black,
                                                isAnimationEnable = true,
                                                labelVisible = true,
                                                labelColor = Color.Black,
                                                isEllipsizeEnabled = true,
                                                chartPadding = 2
                                            ),
                                            onSliceClick = { it ->
                                                val dataDonut = DataDonutChart()

                                                dataChart.data?.forEach { donut ->
                                                    if (it.label == donut.label) {
                                                        dataDonut.label = donut.label
                                                        dataDonut.percentage = donut.percentage
                                                        dataDonut.listDataDonut =
                                                            donut.listDataDonut
                                                    }
                                                }
                                                onDetailChart(dataDonut)
                                            })
                                    }
                                }
                            }

                            is DataChart.LineChartData -> {
                                val entries = arrayListOf<Point>()

                                for (i in 0 until dataChart.data.month?.size!!) {
                                    entries.add(
                                        Point(
                                            i.plus(1).toFloat(),
                                            dataChart.data.month[i].toFloat()
                                        )
                                    )
                                }
                                Log.d("entries Line", "list: $entries")

                                val dataLine = listOf(
                                    Line(dataPoints = entries)
                                )
                                val linePlotData = LinePlotData(lines = dataLine)
                                val lineChartData = LineChartData(linePlotData = linePlotData)

                                Column(
                                    modifier = Modifier.wrapContentSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.height(20.dp))

                                    Text(
                                        text = stringResource(id = R.string.lbl_line_chart),
                                        fontSize = 18.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Box(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .width(200.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        LineChart(
                                            modifier = Modifier.wrapContentSize(),
                                            lineChartData
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
