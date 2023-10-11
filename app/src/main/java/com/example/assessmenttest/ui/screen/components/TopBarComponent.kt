package com.example.assessmenttest.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assessmenttest.ui.theme.white

@Composable
fun TopBarComponent(
    title: String,
    foregroundColor: Color = MaterialTheme.colors.onSurface,
    backgroundColor: Color = MaterialTheme.colors.surface,
    leftIcon: Int = 0,
    textAlignment: TextAlign = TextAlign.Start,
    onLeftIconClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leftIcon != 0) {
                IconButton(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    onClick = onLeftIconClicked
                ) {
                    Icon(
                        tint = foregroundColor,
                        painter = painterResource(id = leftIcon),
                        contentDescription = "Right Icon"
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(end = if (leftIcon != 0) 56.dp else 0.dp)
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp, top = 8.dp, bottom = 6.dp),
                text = title,
                color = foregroundColor,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
                textAlign = textAlignment
            )
        }
        Divider(
            modifier = Modifier.alpha(.15f),
            thickness = .6.dp,
            color = white,
        )
    }
}