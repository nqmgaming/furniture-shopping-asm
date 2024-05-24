package com.nqmgaming.furniture.presentation.main.cart

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.common.components.QuantityButton
import com.nqmgaming.furniture.presentation.Screen
import com.nqmgaming.furniture.presentation.main.cart.components.CartItem
import com.nqmgaming.furniture.ui.theme.BackgroundCategory
import com.nqmgaming.furniture.ui.theme.BlackText
import com.nqmgaming.furniture.ui.theme.GreyText
import com.nqmgaming.furniture.ui.theme.PrimaryColor
import com.nqmgaming.furniture.ui.theme.WhiteText
import com.nqmgaming.furniture.ui.theme.gelasioFont
import com.nqmgaming.furniture.ui.theme.nunitoSansBoldFont
import com.nqmgaming.furniture.ui.theme.nunitoSansFont

@Composable
fun CartScreen(
    navController: NavController = rememberNavController(),
    viewModel: CartViewModel = hiltViewModel()
) {


    val cartList by viewModel.cartList.collectAsState(initial = emptyList())
    val total by viewModel.total.collectAsState(initial = 0.0)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = "Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.my_cart).uppercase(),
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                lineHeight = 25.sp,
                                fontFamily = gelasioFont,
                                color = PrimaryColor
                            )
                        )
                    }
                }
            }

            items(cartList.size) { index ->
                val cartItem = cartList[index]
                CartItem(
                    cartItem = cartItem,
                    onRemoveClick = {
//                        viewModel.removeCartItem(it)
                    },
                    onQuantityDecrease = {
//                        viewModel.decreaseQuantity(it)
                    },
                    onQuantityIncrease = {
//                        viewModel.increaseQuantity(it)
                    },
                    total = total.toDouble()
                )

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CartScreenPreview() {
    CartScreen()
}
