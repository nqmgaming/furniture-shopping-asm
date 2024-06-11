package com.nqmgaming.furniture.presentation.main.order.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nqmgaming.furniture.core.theme.BlackText
import com.nqmgaming.furniture.core.theme.GreyText
import com.nqmgaming.furniture.core.theme.WhiteText
import com.nqmgaming.furniture.domain.model.order.Order

@Composable
fun OrderItem(
    modifier: Modifier = Modifier,
    orderItem: Order
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = "Order No #${orderItem.orderId}",
                    modifier = modifier,
                    style = TextStyle(
                        color = BlackText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                )
                Text(
                    text = "${orderItem.orderDate}",
                    modifier = modifier,
                    style = TextStyle(
                        color = GreyText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    ), maxLines = 1

                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = GreyText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal

                            )
                        ) {
                            append("Quantity: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = BlackText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold

                            )
                        ) {
                            append("${orderItem.quantity}")
                        }
                    }

                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = GreyText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal

                            )
                        ) {
                            append("Total Amount: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = BlackText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold

                            )
                        ) {
                            append("${orderItem.total}")
                        }
                    },
                    modifier = modifier

                )

            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            color = BlackText
                        )
                        .clip(
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable {

                        },
                ) {
                    Text(
                        text = "Detail",
                        modifier = Modifier
                            .padding(horizontal = 30.dp, vertical = 10.dp)
                            .clip(
                                shape = RoundedCornerShape(10.dp)
                            ),
                        style = TextStyle(
                            color = WhiteText
                        )

                    )

                }

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = GreyText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal

                            )
                        ) {
                            append("Status: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Green.copy(
                                    alpha = 0.5f
                                ),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold

                            )
                        ) {
                            append("${orderItem.status}")
                        }
                    },
                    modifier = Modifier,


                    )

            }
        }
    }
}
