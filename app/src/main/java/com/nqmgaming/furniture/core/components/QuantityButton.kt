package com.nqmgaming.furniture.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.core.theme.BlackText
import com.nqmgaming.furniture.core.theme.QuantityColor
import com.nqmgaming.furniture.core.theme.nunitoSansFont

@Composable
fun QuantityButton(
    modifier: Modifier = Modifier,
    quantity: Int = 1,
    onQuantityIncrease: (Int) -> Unit,
    onQuantityDecrease: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                onQuantityIncrease(quantity)
            },
            modifier = Modifier
                .padding(start = 20.dp)
                .background(QuantityColor, RoundedCornerShape(10.dp))
                .size(35.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.increment)
            )
        }
        Text(
            text = "$quantity",
            maxLines = 1,
            style = TextStyle(
                color = BlackText,
                fontSize = 18.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = nunitoSansFont
            ),
            modifier = Modifier.padding(start = 20.dp)
        )
        IconButton(
            onClick = {
                onQuantityDecrease(quantity)
            },
            modifier = Modifier
                .padding(start = 20.dp)
                .background(QuantityColor, RoundedCornerShape(10.dp))
                .size(35.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = stringResource(id = R.string.decrement)
            )
        }

    }
}