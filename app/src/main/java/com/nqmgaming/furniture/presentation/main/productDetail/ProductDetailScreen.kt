package com.nqmgaming.furniture.presentation.main.productDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.common.components.LoadingDialog
import com.nqmgaming.furniture.common.components.QuantityButton
import com.nqmgaming.furniture.presentation.main.productDetail.components.AutoSlidingCarousel
import com.nqmgaming.furniture.presentation.main.productDetail.components.SelectColor
import com.nqmgaming.furniture.ui.theme.BlackText
import com.nqmgaming.furniture.ui.theme.GreyText
import com.nqmgaming.furniture.ui.theme.QuantityColor
import com.nqmgaming.furniture.ui.theme.gelasioFont
import com.nqmgaming.furniture.ui.theme.nunitoSansFont

@Composable
fun ProductDetailScreen(
    navController: NavController?,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {
    val product = viewModel.product.collectAsState().value
    val isFavorite by viewModel.isFavorite.collectAsState(initial = false)
    val isLoading by viewModel.isLoading.collectAsState(initial = false)
    if (isLoading) {
        LoadingDialog()
    }
    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 40.dp)
        ) {
            Box {
                Box(
                    modifier = Modifier
                        .padding(start = 20.dp)

                ) {
                    Card(
                        modifier = Modifier.padding(start = 40.dp),
                        shape = RoundedCornerShape(bottomStart = 60.dp),
                    ) {
                        if (product != null) {
                            AutoSlidingCarousel(
                                itemsCount = product.images.size,
                                itemContent = { index ->
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(product.images[index])
                                            .build(),
                                        contentDescription = null,
                                        contentScale = ContentScale.FillWidth,
                                        modifier = Modifier.height(450.dp)
                                    )
                                }
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.padding(top = 20.dp)
                    ) {
                        Button(
                            onClick = {
                                navController?.popBackStack()
                            },
                            modifier = Modifier
                                .size(60.dp)
                                .width(60.dp)
                                .height(60.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 0.2.dp,
                                pressedElevation = 0.dp
                            ),
                            shape = RoundedCornerShape(20.dp),
                            content = {
                                Icon(
                                    imageVector = Icons.Default.ArrowBackIosNew,
                                    contentDescription = "Back",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .width(60.dp)
                                        .height(60.dp)
                                )
                            },
                            contentPadding = PaddingValues(15.dp)
                        )
                        var colorSelect by remember { mutableIntStateOf(0) }
                        SelectColor(
                            product = product,
                            selectIndex = colorSelect,
                            onClick = {
                                colorSelect = it
                            }
                        )

                    }
                }

            }

            Text(
                text = product?.name ?: "Loading",
                maxLines = 1,
                style = TextStyle(
                    color = BlackText,
                    fontSize = 24.sp,
                    lineHeight = 30.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = gelasioFont
                ),
                modifier = Modifier.padding(start = 20.dp, top = 20.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                var quantity by remember { mutableIntStateOf(1) }
                Text(
                    text = "$ ${product?.price}",
                    maxLines = 1,
                    style = TextStyle(
                        color = BlackText,
                        fontSize = 30.sp,
                        lineHeight = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = nunitoSansFont
                    ),
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

                QuantityButton(
                    onQuantityIncrease = {
                        quantity += 1
                    },
                    onQuantityDecrease = {
                        if (quantity > 1) {
                            quantity -= 1
                        }
                    },
                    quantity = quantity,
                    modifier = Modifier.padding(end = 20.dp)
                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 20.dp, top = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = stringResource(id = R.string.star),
                    tint = Color(0XFFFFD700),
                )

                Text(
                    text = "4.5",
                    maxLines = 1,
                    style = TextStyle(
                        color = BlackText,
                        fontSize = 18.sp,
                        lineHeight = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = nunitoSansFont
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )

                Text(
                    text = "(50 reviews)",
                    maxLines = 1,
                    style = TextStyle(
                        color = GreyText,
                        fontSize = 16.sp,
                        lineHeight = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = nunitoSansFont
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )


            }

            Text(
                text = product?.description ?: "Loading",
                style = TextStyle(
                    color = GreyText,
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = nunitoSansFont
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        viewModel.onFavoriteClick()
                    },
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .background(QuantityColor, RoundedCornerShape(10.dp))
                        .size(55.dp),
                    enabled = !isLoading
                ) {
                    Icon(
                        imageVector = Icons.Default.BookmarkBorder,
                        contentDescription = stringResource(id = R.string.increment),
                        tint = if (isFavorite) Color(0XFFFFD700) else Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .weight(1f),
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
                            text = "Add to Cart",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 18.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = nunitoSansFont
                            )
                        )
                    },
                    contentPadding = PaddingValues(15.dp)
                )

            }
        }
    }
}