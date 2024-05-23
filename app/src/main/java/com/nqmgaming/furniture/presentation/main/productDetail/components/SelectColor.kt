package com.nqmgaming.furniture.presentation.main.productDetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nqmgaming.furniture.domain.model.product.Product
import com.nqmgaming.furniture.ui.theme.BlackText
import com.nqmgaming.furniture.ui.theme.GreyLight

@Composable
fun SelectColor(
    product: Product?,
    onClick: (Int) -> Unit,
    selectIndex: Int
) {
    Box(
        modifier = Modifier
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(Color.White)
            .zIndex(1f)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            product?.colors?.forEachIndexed { index, color ->
                ItemColor(
                    color = product.colorFromString(color),
                    isSelected = index == selectIndex,
                    onClick = {
                        onClick(index)
                    },
                    selectIndex = selectIndex
                )

            }
        }
    }
}

@Composable
private fun ItemColor(
    color: Color,
    isSelected: Boolean,
    onClick: (Int) -> Unit = {},
    selectIndex: Int
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip((CircleShape))
            .padding(10.dp)
            .border(
                5.dp,
                if (isSelected) Brush.verticalGradient(
                    listOf(
                        BlackText,
                        BlackText
                    )
                ) else Brush.verticalGradient(
                    listOf(
                        GreyLight,
                        GreyLight
                    )
                ),
                CircleShape
            )
            .clickable {
                onClick(selectIndex)
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(color)
        )
    }
}