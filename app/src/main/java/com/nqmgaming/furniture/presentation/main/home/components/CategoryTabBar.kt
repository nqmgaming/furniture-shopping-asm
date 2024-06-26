package com.nqmgaming.furniture.presentation.main.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nqmgaming.furniture.domain.model.category.Category
import com.nqmgaming.furniture.core.theme.BackgroundCategory
import com.nqmgaming.furniture.core.theme.BlackText
import com.nqmgaming.furniture.core.theme.GreyLight
import com.nqmgaming.furniture.core.theme.PrimaryColor
import com.nqmgaming.furniture.core.theme.WhiteText
import com.nqmgaming.furniture.core.theme.nunitoSansFont

@Composable
fun CategoryTabBar(
    modifier: Modifier = Modifier,
    selectCategory: Int = 0,
    onChangeSelected: (Int) -> Unit = {}
) {

    Row(
        modifier = modifier.horizontalScroll(rememberScrollState())
    ) {
        Category().categoriesList().forEachIndexed { index, category ->
            CategoryItems(
                category = category,
                onChangeSelected = {
                    onChangeSelected(index)
                },
                selected = selectCategory == index
            )
        }
    }
}

@Composable
private fun CategoryItems(
    category: Category,
    modifier: Modifier = Modifier,
    onChangeSelected: (Category) -> Unit = {},
    selected: Boolean = false
) {
    Box(
        modifier = modifier
            .size(70.dp)
            .clickable {
                onChangeSelected(category)
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        if (selected) {
                            Brush.horizontalGradient(
                                colors = listOf(
                                    BlackText,
                                    BlackText
                                )
                            )
                        } else {
                            Brush.horizontalGradient(
                                colors = listOf(
                                    BackgroundCategory,
                                    BackgroundCategory
                                )
                            )
                        },

                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = category.icon),
                    contentDescription = category.title,
                    tint = if (selected) WhiteText else GreyLight,
                )

            }
            Text(
                text = category.title, style = TextStyle(
                    color = if (selected) PrimaryColor else BlackText,
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 14.sp,
                    fontFamily = nunitoSansFont
                )
            )
        }
    }
}
