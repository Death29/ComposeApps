package com.example.assessmenttest.ui.screen.home.chart.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.assessmenttes.R
import com.example.assessmenttest.model.network.DataDonutChart
import com.example.assessmenttest.model.network.DetailDataDonut
import com.example.assessmenttest.ui.screen.components.TopBarComponent
import java.text.NumberFormat
import java.util.Currency

@Composable
fun DetailChartScreen(data: DataDonutChart) {
    androidx.compose.material.Scaffold(
        topBar = { TopBarComponent(title = stringResource(id = R.string.lbl_detail_chart_donut)) },
        backgroundColor = MaterialTheme.colors.surface
    ) { paddingValues ->

        Column(modifier = Modifier.wrapContentSize(),
            horizontalAlignment = CenterHorizontally) {

            Text(text = data.label, style = androidx.compose.material3.MaterialTheme.typography.titleLarge, )
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(contentPadding = paddingValues) {
                itemsIndexed(items = data.listDataDonut) { _, data ->
                               ItemListChart(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp),
                        dataChild = data
                    )
                }
            }
        }
    }
}

@Composable
fun ItemListChart(
    modifier: Modifier = Modifier,
    dataChild: DetailDataDonut? = null,
) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {

        val amount = dataChild?.nominal?.let { formatCurrency(it) }
        Text( text = dataChild?.trxDate.toString(), textAlign = TextAlign.Start, style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
        Text(text = amount.toString(), textAlign = TextAlign.End, style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
    }
}

fun formatCurrency(amount: Long):String{
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.currency = Currency.getInstance("IDR")

    return format.format(amount)
}
