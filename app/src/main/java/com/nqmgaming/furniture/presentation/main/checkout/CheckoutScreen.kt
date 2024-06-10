package com.nqmgaming.furniture.presentation.main.checkout

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.presentation.main.cart.CartViewModel
import com.nqmgaming.furniture.presentation.main.checkout.components.AddressItem
import com.nqmgaming.furniture.presentation.main.checkout.components.DeliveryItem
import com.nqmgaming.furniture.presentation.main.checkout.components.PaymentItem
import com.nqmgaming.furniture.ui.theme.BlackText
import com.nqmgaming.furniture.ui.theme.GreyLight
import com.nqmgaming.furniture.ui.theme.GreyText
import com.nqmgaming.furniture.ui.theme.PrimaryColor
import com.nqmgaming.furniture.ui.theme.WhiteText
import com.nqmgaming.furniture.ui.theme.gelasioFont
import com.nqmgaming.furniture.ui.theme.nunitoSansBoldFont
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CheckoutScreen(
    navController: NavController,
    checkoutViewModel: CheckoutViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {

    val navigate by checkoutViewModel.navigate.collectAsState(initial = false)

    val context = LocalContext.current
    LaunchedEffect(true) {
        checkoutViewModel.navigate.collectLatest { navigate ->
            if (navigate) {
                Toast.makeText(context, "Order Placed", Toast.LENGTH_SHORT).show()
            } else {

            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = stringResource(
                            id = R.string.search
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .size(20.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(10f)
                            .padding(5.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.checkout).uppercase(),
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
            item {
                AddressItem()
            }
            item {
                Spacer(modifier = Modifier.size(20.dp))
            }
            item {
              PaymentItem()
            }
            item {
                Spacer(modifier = Modifier.size(20.dp))
            }
            // Delivery Method
            item {
               DeliveryItem()
            }

            // Order Summary
            item {
                Spacer(modifier = Modifier.size(20.dp))
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Order Summary",
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
                                text = "Total",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 20.sp,
                                    fontFamily = nunitoSansBoldFont,
                                    color = BlackText
                                )
                            )
                            Text(
                                text = "$100",
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
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Shipping",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    lineHeight = 20.sp,
                                    fontFamily = nunitoSansBoldFont,
                                    color = GreyText
                                )
                            )
                            Text(
                                text = "Free",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    lineHeight = 20.sp,
                                    fontFamily = nunitoSansBoldFont,
                                )
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Discount",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    lineHeight = 20.sp,
                                    fontFamily = nunitoSansBoldFont,
                                    color = GreyText
                                )
                            )
                            Text(
                                text = "-$10",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    lineHeight = 20.sp,
                                    fontFamily = nunitoSansBoldFont,
                                    color = Color.Red
                                )
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Total",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 20.sp,
                                    fontFamily = nunitoSansBoldFont,
                                    color = BlackText
                                )
                            )
                            Text(
                                text = "$90",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 20.sp,
                                    fontFamily = nunitoSansBoldFont,
                                )
                            )
                        }
                    }
                }
            }

            // Button
            item {
                Spacer(modifier = Modifier.size(20.dp))
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            checkoutViewModel.onTotalChange(
                                total = cartViewModel.onGetAllTotal()
                            )
                            checkoutViewModel.onOrderDateChange(
                                orderDate = System
                                    .currentTimeMillis()
                                    .toString()
                            )
                            checkoutViewModel.onQuantityChange(
                                quantity = cartViewModel.onGetAllQuantity()
                            )
                            checkoutViewModel.onStatusChange(
                                status = "Pending"
                            )
                            checkoutViewModel.onProductIdChange(
                                productId = cartViewModel.onGetAllProductId()
                            )

                            cartViewModel.onRemoveAllFromCart()

                            checkoutViewModel.onCreateOrder()

                        },
                    colors = CardDefaults.cardColors(
                        containerColor = PrimaryColor
                    ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 2.dp
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Place Order",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 20.sp,
                                fontFamily = nunitoSansBoldFont,
                                color = WhiteText
                            )
                        )
                    }
                }
            }
        }
    }
}
