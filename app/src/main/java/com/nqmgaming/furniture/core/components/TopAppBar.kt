package com.nqmgaming.furniture.core.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.core.theme.PrimaryColor
import com.nqmgaming.furniture.core.theme.gelasioFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    @StringRes title: Int,
    onLeftIconClick: () -> Unit,
    @DrawableRes leftIcon: Int = R.drawable.ic_back,
    onRightIconClick: () -> Unit,
    @DrawableRes rightIcon: Int = 0
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        title = {
            Text(
                text = stringResource(id = title).uppercase(),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 25.sp,
                    fontFamily = gelasioFont,
                    color = PrimaryColor
                )
            )
        },
        navigationIcon = {
            if (leftIcon != 0) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = leftIcon),
                        contentDescription = stringResource(id = R.string.back),
                        modifier = Modifier.clickable { onLeftIconClick() }.size(24.dp)
                    )
                }
            }
        },
        actions = {
            if (rightIcon != 0) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painter = painterResource(id = rightIcon),
                        contentDescription = stringResource(id = R.string.search),
                        modifier = Modifier.clickable { onRightIconClick() }
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    TopAppBar(
        title = R.string.checkout,
        onRightIconClick = { /*TODO*/ },
        onLeftIconClick = { /*TODO*/ },
        leftIcon = R.drawable.ic_back,
    )
}