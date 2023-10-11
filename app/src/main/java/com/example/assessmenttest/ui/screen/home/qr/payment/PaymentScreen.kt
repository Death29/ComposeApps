package com.example.assessmenttest.ui.screen.home.qr.payment

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.extensions.isNotNull
import com.example.assessmenttes.R
import com.example.assessmenttest.ui.screen.components.TopBarComponent
import com.example.assessmenttest.ui.screen.home.chart.detail.formatCurrency
import com.example.assessmenttest.ui.screen.home.qr.util.ViewModelDataPayment
import com.example.assessmenttest.ui.screen.home.qr.util.ViewModelLocal
import com.example.testindocyber.model.network.room.InformationUser
import com.example.testindocyber.model.network.room.Payment
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    data: String,
    onSuccessPayment: () -> Unit,
    viewModel: ViewModelLocal,
    viewModelPayment: ViewModelDataPayment
) {
    var saving = 0.0

    val user = viewModel.dataUser.collectAsState(initial = null).value
    val statePayment = viewModelPayment.dataPayment.collectAsState(initial = emptyList()).value
    var listPayment = mutableListOf<Payment>()

    if (user == null) {
        saving = 1000000.0

        val dataEntry = InformationUser(0, saving)
        viewModel.insertDataUser(dataEntry)
    } else {
        saving = user.saving
    }
    val savingToText = formatCurrency(saving)


    if (statePayment.isEmpty()) Log.d("list Paymnet", "List null")
    else listPayment = statePayment.toCollection(ArrayList())

    androidx.compose.material.Scaffold(topBar = { TopBarComponent(title = stringResource(id = R.string.lbl_detail_payment) ) }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.lbl_detail_payment),
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                PaymentDetailItem(label = R.string.lbl_saving, value = savingToText)
                PaymentDetailItem(
                    label = R.string.lbl_bank_destination,
                    value = data.subSequence(0, 3).toString()
                )
                PaymentDetailItem(
                    label = R.string.lbl_id_trans,
                    value = data.subSequence(4, 13).toString()
                )
                PaymentDetailItem(
                    label = R.string.lbl_merchant,
                    value = data.subSequence(15, 33).toString()
                )
                PaymentDetailItem(
                    label = R.string.lbl_nominal,
                    value = data.substringAfter("TEST.")
                )

                Button(
                    onClick = {
                        listPayment.add(
                            Payment(
                                id = 0,
                                nameTransaction = "Transfer",
                                amount = data.substringAfter("TEST.").toDouble()
                            )
                        )
                        viewModelPayment.insertPayment(listPayment)

                        user?.let { infoUser ->
                            val updateUser = InformationUser(
                                id = infoUser.id,
                                saving = infoUser.saving.minus(
                                    data.substringAfter("TEST.").toDouble()
                                )
                            )
                            viewModel.updateDataUser(updateUser)

                            Log.d("dataUser", updateUser.toString())
                        }
                        onSuccessPayment()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(text = "Bayar", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun PaymentDetailItem(label: Int, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = label),
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            color = Color.Black,
            fontSize = 14.sp
        )
    }
}

fun formatCurrency(amount: Double): String {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.currency = Currency.getInstance("IDR")

    return format.format(amount)
}