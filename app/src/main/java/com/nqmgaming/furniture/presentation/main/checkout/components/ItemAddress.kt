package com.nqmgaming.furniture.presentation.main.checkout.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.core.theme.BlackText
import com.nqmgaming.furniture.core.theme.GreyLight
import com.nqmgaming.furniture.core.theme.GreyText
import com.nqmgaming.furniture.core.theme.nunitoSansBoldFont

@Composable
fun AddressItem(
    modifier: Modifier = Modifier,
    name: String
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Shipping Address",
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
                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 20.sp,
                        fontFamily = nunitoSansBoldFont,
                        color = BlackText
                    )
                )
                Text(
                    text = "-----------------------------------------------",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 20.sp,
                        fontFamily = nunitoSansBoldFont,
                        color = BlackText
                    ),
                    maxLines = 1
                )
                Text(
                    text = "25 rue Robert Latouche, Nice, 06200, Côte D’azur, France",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp,
                        fontFamily = nunitoSansBoldFont,
                        color = GreyText
                    )

                )
            }
        }

    }
}