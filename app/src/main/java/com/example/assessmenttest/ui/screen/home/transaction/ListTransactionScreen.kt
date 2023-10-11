package com.example.assessmenttest.ui.screen.home.transaction

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.assessmenttes.R
import com.example.assessmenttest.model.network.DetailDataDonut
import com.example.assessmenttest.ui.screen.components.TopBarComponent
import com.example.assessmenttest.ui.screen.home.chart.detail.formatCurrency
import com.example.assessmenttest.ui.screen.home.qr.util.ViewModelDataPayment
import com.example.testindocyber.model.network.room.Payment

@Composable
fun HistoryTransactionScreen(
    viewModelPayment: ViewModelDataPayment
){
    val statePayment = viewModelPayment.dataPayment.collectAsState(initial = emptyList()).value
    var listPayment = mutableListOf<Payment>()

    if (statePayment.isEmpty()) Log.d("list Paymnet", "List null")
    else listPayment = statePayment.toCollection(ArrayList())

    Scaffold(topBar = { TopBarComponent(title = stringResource(id = R.string.lbl_history_transaksi))}) {paddingValues ->
        LazyColumn(contentPadding = paddingValues){
            itemsIndexed(items = listPayment){_, data ->
                ItemListPayment(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp),
                    dataChild = data
                )
            }
        }
    }
}

@Composable
fun ItemListPayment(
    modifier: Modifier = Modifier,
    dataChild: Payment? = null,
) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {

        val amount = dataChild?.amount?.let { com.example.assessmenttest.ui.screen.home.qr.payment.formatCurrency(it) }
        Text( text = dataChild?.nameTransaction.toString(), textAlign = TextAlign.Start, style = MaterialTheme.typography.titleMedium)
        Text(text = amount.toString(), textAlign = TextAlign.End, style = MaterialTheme.typography.titleMedium)
    }
}