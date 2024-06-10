package com.nqmgaming.furniture.presentation.main.checkout.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.nqmgaming.furniture.ui.theme.GreyText
import com.nqmgaming.furniture.ui.theme.nunitoSansBoldFont

@Composable
fun DeliveryItem(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Delivery Method",
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Standard Delivery",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 20.sp,
                        fontFamily = nunitoSansBoldFont,
                        color = BlackText
                    )
                )
                Text(
                    text = "Free",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 20.sp,
                        fontFamily = nunitoSansBoldFont,
                    )
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delivery),
                    contentDescription = stringResource(
                        id = R.string.delivery
                    ),
                    tint = Color.Red,
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = "Estimated delivery: 3-5 days",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp,
                        fontFamily = nunitoSansBoldFont,
                        color = GreyText
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .weight(1f),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}