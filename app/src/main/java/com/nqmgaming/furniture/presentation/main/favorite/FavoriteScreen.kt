package com.nqmgaming.furniture.presentation.main.favorite

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nqmgaming.furniture.R
import com.nqmgaming.furniture.util.SharedPrefUtils
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val userId = SharedPrefUtils.getInt(context, "userId", 0)
    val lifecycleOwner: LifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            if (event === Lifecycle.Event.ON_START) {
                // Start a coroutine using the lifecycle's coroutine scope
                lifecycleOwner.lifecycleScope.launch {
                    viewModel.getFavorites(userId = userId)
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    val favoriteList = viewModel.favoriteList.collectAsState(listOf()).value
    Log.d("FavoriteScreen", "FavoriteList: $favoriteList")
    Text(text = stringResource(id = R.string.favorites))
}
