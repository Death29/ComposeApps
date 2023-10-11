package com.example.assessmenttest.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.assessmenttes.R

@Composable
fun HomeScreen(
    onPromoClick:()-> Unit,
    onHistoryTransactionClick: () -> Unit,
    onScanQrClick: ()-> Unit,
    onChartClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Button(
                    onClick = {onPromoClick()},
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp),
                    content = { Text(stringResource(id = R.string.lbl_btn_promo)) },
                )
                Button(
                    onClick = {onScanQrClick()},
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 5.dp),
                    content = { Text(stringResource(id = R.string.lbl_btn_qr)) }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Button(
                    onClick = { onHistoryTransactionClick() },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp),
                    content = { Text(stringResource(id =R.string.lbl_history_transaksi)) }
                )
                Button(
                    onClick = {onChartClick()},
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 5.dp),
                    content = { Text(stringResource(id = R.string.lbl_chart)) }
                )
            }
        }
    }
}