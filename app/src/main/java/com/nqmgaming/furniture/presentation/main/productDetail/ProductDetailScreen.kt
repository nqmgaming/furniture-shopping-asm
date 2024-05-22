package com.nqmgaming.furniture.presentation.main.productDetail

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun ProductDetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    productId: Int?
) {
    val product = viewModel.product.collectAsState().value
    Log.d("ProductDetailScreen", "ProductDetailScreen: $product")
    if (product != null) {
        AsyncImage(
            model = product.images.first(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(300.dp)
                .width(300.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }
}
