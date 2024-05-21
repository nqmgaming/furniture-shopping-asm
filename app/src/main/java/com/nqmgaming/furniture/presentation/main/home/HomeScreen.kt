package com.nqmgaming.furniture.presentation.main.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.presentation.main.home.components.CategoryTabBar
import com.nqmgaming.furniture.presentation.main.home.components.ProductCart
import com.nqmgaming.furniture.ui.theme.GreyLight
import com.nqmgaming.furniture.ui.theme.PrimaryColor
import com.nqmgaming.furniture.ui.theme.gelasioFont
import com.nqmgaming.furniture.ui.theme.merriweatherFont

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val isLoading by viewModel.isLoading.collectAsState(initial = false)
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var categorySelect by rememberSaveable {
        mutableIntStateOf(0)
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            if (event === Lifecycle.Event.ON_START) {
                viewModel.getProducts()
            }

        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(
                    id = R.string.search
                ),
                modifier = Modifier.weight(1f)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(10f)
                    .padding(5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.make_home),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 25.sp,
                        fontFamily = gelasioFont,
                        color = GreyLight
                    )
                )
                Text(
                    text = stringResource(id = R.string.beautiful).uppercase(),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 25.sp,
                        fontFamily = gelasioFont,
                        color = PrimaryColor
                    )
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_cart),
                contentDescription = stringResource(
                    id = R.string.cart
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
            )
        }

        CategoryTabBar(
            modifier = Modifier
                .padding(start = 16.dp),
            onChangeSelected = {
                categorySelect = it
            })

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val productList = viewModel.productList.collectAsState(initial = listOf()).value
            Log.d("HomeScreen", "ProductList: $productList")
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.Center,
                contentPadding = PaddingValues(start = 30.dp, end = 0.dp)
            ) {
                if (categorySelect == 0) {
                    productList?.forEach { product ->
                        item {
                            ProductCart(
                                product = product,
                                onAddToCart = {
                                },
                                navController = navController
                            )
                        }
                    }
                } else {
                    productList?.filter {
                        it.categoryId == categorySelect
                    }?.forEach { product ->
                        item {
                            ProductCart(
                                product = product,
                                onAddToCart = {
                                },
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}