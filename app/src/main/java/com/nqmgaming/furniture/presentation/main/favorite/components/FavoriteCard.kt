package com.nqmgaming.furniture.presentation.main.favorite.components

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.domain.model.favorite.Favorite
import com.nqmgaming.furniture.domain.model.product.Product
import com.nqmgaming.furniture.ui.theme.BackgroundCategory
import com.nqmgaming.furniture.ui.theme.BlackText
import com.nqmgaming.furniture.ui.theme.GreyText
import com.nqmgaming.furniture.ui.theme.WhiteText
import com.nqmgaming.furniture.ui.theme.nunitoSansFont

@Composable
fun FavoriteCard(
    modifier: Modifier = Modifier,
    product: Product,
    onFavoriteClick: (Product) -> Unit = {},
    onDeleteFavoriteClick: (Product) -> Unit = {},
    onAddFavoriteToBagClick: (Product) -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(bottom = 10.dp, start = 16.dp, end = 16.dp)
            .clickable {
                onFavoriteClick(product)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = product!!.images.first(),
                contentDescription = product.name,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
                clipToBounds = true
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = product.name, style = TextStyle(
                        fontFamily = nunitoSansFont,
                        color = GreyText,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier,
                    textAlign = TextAlign.Start,
                    maxLines = 1
                )
                Text(
                    text = "$ ${product.price}.00", style = TextStyle(
                        fontFamily = nunitoSansFont,
                        color = BlackText,
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(bottom = 20.dp),
                    maxLines = 1
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .background(Color.White, CircleShape)
                        .border(
                            width = 1.dp,
                            color = GreyText,
                            shape = CircleShape
                        )
                        .clickable {
                            onDeleteFavoriteClick(product)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(id = R.string.remove),
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .background(
                            color = GreyText,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shopping_bag),
                        contentDescription = stringResource(
                            id = R.string.add_to_cart
                        ),
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                onAddFavoriteToBagClick(product)
                            },
                        tint = WhiteText
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