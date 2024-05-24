package com.nqmgaming.furniture.presentation.main.cart.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.common.components.QuantityButton
import com.nqmgaming.furniture.domain.model.cart.Cart
import com.nqmgaming.furniture.ui.theme.BackgroundCategory
import com.nqmgaming.furniture.ui.theme.BlackText
import com.nqmgaming.furniture.ui.theme.GreyText
import com.nqmgaming.furniture.ui.theme.nunitoSansBoldFont
import com.nqmgaming.furniture.ui.theme.nunitoSansFont

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    cartItem: Cart,
    total: Double,
    onRemoveClick: (Cart) -> Unit = {},
    onQuantityDecrease: (Cart) -> Unit = {},
    onQuantityIncrease: (Cart) -> Unit = {}
) {
    Log.d("CartItem", "CartItem Total: $total")
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .clickable {
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 0.dp
        ),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier,
        ) {
            AsyncImage(
                model = cartItem.product.images.firstOrNull(),
                contentDescription = cartItem.product.name,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        BorderStroke(4.dp, cartItem.product.colorFromString(cartItem.colorString)),
                        RoundedCornerShape(10.dp)
                    ),
                contentScale = ContentScale.Crop,
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(42.dp)
            ) {

                Row() {
                    Text(
                        text = cartItem.product.name,
                        style = TextStyle(
                            fontFamily = nunitoSansFont,
                            color = BlackText,
                            fontSize = 18.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .width(22.dp)
                            .height(22.dp)
                            .background(Color.White, CircleShape)
                            .border(
                                width = 1.dp,
                                color = GreyText,
                                shape = CircleShape
                            )
                            .clickable {
                                onRemoveClick(cartItem)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(id = R.string.remove_cart),
                            tint = GreyText
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    QuantityButton(
                        modifier = Modifier.weight(1f),
                        quantity = cartItem.quantity,
                        onQuantityDecrease = {
                            onQuantityDecrease(cartItem)
                        },
                        onQuantityIncrease = {
                            onQuantityIncrease(cartItem)
                        }
                    )

                    Text(
                        text = "$ ${cartItem.product.price * cartItem.quantity}",
                        style = TextStyle(
                            fontFamily = nunitoSansBoldFont,
                            color = BlackText,
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        ),
                        modifier = Modifier
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .background(BackgroundCategory)
                .height(1.dp)
        ) {
            Spacer(modifier = Modifier.height(1.dp))
        }
    }
}