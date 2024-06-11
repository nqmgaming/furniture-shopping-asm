package com.nqmgaming.furniture.presentation.main.checkout

import android.Manifest
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.presentation.Screen
import com.nqmgaming.furniture.presentation.main.cart.CartViewModel
import com.nqmgaming.furniture.presentation.main.checkout.components.AddressItem
import com.nqmgaming.furniture.presentation.main.checkout.components.DeliveryItem
import com.nqmgaming.furniture.presentation.main.checkout.components.PaymentItem
import com.nqmgaming.furniture.core.theme.BlackText
import com.nqmgaming.furniture.core.theme.GreyLight
import com.nqmgaming.furniture.core.theme.GreyText
import com.nqmgaming.furniture.core.theme.PrimaryColor
import com.nqmgaming.furniture.core.theme.WhiteText
import com.nqmgaming.furniture.core.theme.gelasioFont
import com.nqmgaming.furniture.core.theme.nunitoSansBoldFont
import com.nqmgaming.furniture.util.NotificationHandler
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckoutScreen(
    navController: NavController,
    checkoutViewModel: CheckoutViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {

    val navigate by checkoutViewModel.navigate.collectAsState(initial = false)

    var isLoading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val total = cartViewModel.onGetAllTotal()
    val discount = (total * 0.1).toInt()
    val finalTotal = total - discount
    val context = LocalContext.current
    val postNotificationPermission =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(key1 = true) {
        if (!postNotificationPermission.status.isGranted) {
            postNotificationPermission.launchPermissionRequest()
        }

    }
    var titleNotification by remember { mutableStateOf("Order Placed") }
    var messageNotification by remember { mutableStateOf("Your order has been placed successfully") }
    val notificationHandler = NotificationHandler(context)
    notificationHandler.showSimpleNotification()
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
                            id = R.string.back
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .size(20.dp)
                            .clickable {
                                navController.navigateUp()
                            }
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
                AddressItem(
                    name = checkoutViewModel.userName ?: "Not Found"
                )
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
                                text = "$${total}",
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
                                text = "-$${discount}",
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
                                text = "$${finalTotal}",
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
                            isLoading = true
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

                            checkoutViewModel.onCreateNotification(
                                title = "Order Placed",
                                message = "Your order has been placed successfully"
                            )

                            cartViewModel.onRemoveAllFromCart()


                            checkoutViewModel.onCreateOrder()

                            isLoading = false

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

        if (navigate) {
            AlertDialog(
                onDismissRequest = {
                    scope.launch {
                        checkoutViewModel.onNavigateChange()
                        navController.popBackStack()
                        navController.popBackStack()
                    }
                },
                title = {
                    Text(
                        text = "Order Placed",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontFamily = nunitoSansBoldFont,
                            color = BlackText
                        )
                    )
                },
                text = {
                    Text(
                        text = "Your order has been placed successfully",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 20.sp,
                            fontFamily = nunitoSansBoldFont,
                            color = GreyText
                        )
                    )
                },
                confirmButton = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                checkoutViewModel.onNavigateChange()
                                navController.navigate(Screen.CheckoutSuccessScreen.route)
                            }
                        }
                    ) {
                        Text(
                            text = "OK",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 20.sp,
                                fontFamily = nunitoSansBoldFont,
                                color = PrimaryColor
                            )
                        )
                    }
                },
            )
        }
    }

}
