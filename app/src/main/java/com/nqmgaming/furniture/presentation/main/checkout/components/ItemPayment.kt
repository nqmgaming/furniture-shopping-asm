package com.nqmgaming.furniture.presentation.main.checkout.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.ui.theme.BlackText
import com.nqmgaming.furniture.ui.theme.GreyLight
import com.nqmgaming.furniture.ui.theme.nunitoSansBoldFont

@Composable
fun PaymentItem(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Payment Method",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 25.sp,
                fontFamily = nunitoSansBoldFont,
                color = GreyLight
            )
        )
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = stringResource(
                    id = R.string.edit
                ),
                modifier = Modifier.size(20.dp)
            )
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_card),
                contentDescription = stringResource(
                    id = R.string.card
                ),
                tint = Color.Red,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "**** **** **** 1234",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = nunitoSansBoldFont,
                    color = BlackText
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f),

                )
        }
    }
}