package com.nqmgaming.furniture.presentation.main.home.components

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.domain.model.product.Product
import com.nqmgaming.furniture.core.theme.BlackText
import com.nqmgaming.furniture.core.theme.GreyText
import com.nqmgaming.furniture.core.theme.WhiteText
import com.nqmgaming.furniture.core.theme.nunitoSansFont

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    onAddToCart: (Product) -> Unit,
    onProductClick: (Product) -> Unit = {},
) {
    Column {
        Box(
            modifier = modifier
                .background(
                    Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable {
                    onProductClick(product)
                },
            contentAlignment = Alignment.CenterStart
        ) {
            AsyncImage(
                model = product.images.first(),
                contentDescription = product.name,
                modifier = Modifier
                    .height(250.dp)
                    .width(160.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
                clipToBounds = true
            )

            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .padding(10.dp)
                    .align(Alignment.BottomEnd)
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
                            onAddToCart(product)
                        },
                    tint = WhiteText
                )
            }
        }
        Text(
            text = product.name, style = TextStyle(
                fontFamily = nunitoSansFont,
                color = GreyText,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(vertical = 10.dp),
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "$ ${product.price}.00", style = TextStyle(
                fontFamily = nunitoSansFont,
                color = BlackText,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 20.dp)
        )

    }
}
