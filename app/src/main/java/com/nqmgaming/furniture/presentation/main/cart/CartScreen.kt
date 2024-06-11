package com.nqmgaming.furniture.presentation.main.cart

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.presentation.Screen
import com.nqmgaming.furniture.presentation.main.cart.components.CartItem
import com.nqmgaming.furniture.core.theme.BlackText
import com.nqmgaming.furniture.core.theme.GreyText
import com.nqmgaming.furniture.core.theme.PrimaryColor
import com.nqmgaming.furniture.core.theme.gelasioFont
import com.nqmgaming.furniture.core.theme.nunitoSansFont

@Composable
fun CartScreen(
    navController: NavController = rememberNavController(),
    viewModel: CartViewModel = hiltViewModel()
) {


    val cartList by viewModel.cartList.collectAsState(initial = emptyList())
    val total by viewModel.total.collectAsState()

    LaunchedEffect(key1 = total) {
        Log.d("CartScreen", "Total: $total")
    }

    ConstraintLayout(
        modifier = Modifier
    ) {
        val (cartContainer, totalContainer) = createRefs()

        LazyColumn(
            modifier = Modifier
                .height(600.dp)
                .padding(horizontal = 16.dp)
                .constrainAs(cartContainer) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(totalContainer.top)
                }
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
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

            if (cartList.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Your cart is empty",
                            style = TextStyle(
                                color = BlackText,
                                fontSize = 20.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = nunitoSansFont
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
                        viewModel.onRemoveCartItem(
                            cartId = it.cartId.toString(),
                            cart = it
                        )
                    },
                    onQuantityDecrease = {
                        viewModel.onDecrementQuantity(it.cartId.toString())
                    },
                    onQuantityIncrease = {
                        viewModel.onIncrementQuantity(it.cartId.toString())
                    },
                    total = total.toDouble()
                )

            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .constrainAs(totalContainer) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Column {
                var promoCode by remember {
                    mutableStateOf("")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 20.dp)
                        .shadow(
                            elevation = 1.dp,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(Color.White),
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = promoCode,
                            onValueChange = {
                                promoCode = it
                            },
                            placeholder = {
                                Text(
                                    text = "Enter Promo Code",
                                    style = TextStyle(
                                        color = GreyText,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Normal,
                                        fontFamily = nunitoSansFont
                                    )
                                )
                            },
                            shape = RoundedCornerShape(30.dp),
                            colors = TextFieldDefaults.colors(
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedTrailingIconColor = Color.Transparent,
                                focusedTrailingIconColor = Color.Transparent,
                                errorTextColor = Color.Red,
                                errorIndicatorColor = Color.Red,
                                errorLeadingIconColor = Color.Red,
                                errorTrailingIconColor = Color.Red,
                                errorPlaceholderColor = Color.Red,
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent
                            ),
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .height(60.dp)
                        )

                        Button(
                            onClick = {
//                            viewModel.onApplyPromoCode(promoCode)
                            },
                            modifier = Modifier
                                .size(60.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 0.2.dp,
                                pressedElevation = 0.dp
                            ),
                            shape = RoundedCornerShape(20.dp),
                            content = {

                                Icon(
                                    imageVector = Icons.Default.ArrowForwardIos,
                                    contentDescription = "Apply",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            contentPadding = PaddingValues(15.dp)
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total:",
                    style = TextStyle(
                        color = GreyText,
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = nunitoSansFont
                    ),

                    )
                Text(
                    text = "$ $total",
                    style = TextStyle(
                        color = BlackText,
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = nunitoSansFont
                    ),
                )
            }
            Button(
                onClick = {
                    navController.navigate(Screen.CheckoutScreen.route + "/${total.toFloat()}")
                },
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 40.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.2.dp,
                    pressedElevation = 0.dp
                ),
                shape = RoundedCornerShape(20.dp),
                content = {
                    Text(
                        text = "CHECK OUT",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            lineHeight = 27.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = nunitoSansFont
                        )
                    )
                },
                contentPadding = PaddingValues(15.dp),
                enabled = cartList.isNotEmpty()
            )

        }
    }
}


