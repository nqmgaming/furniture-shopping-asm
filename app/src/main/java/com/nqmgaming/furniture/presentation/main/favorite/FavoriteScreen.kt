package com.nqmgaming.furniture.presentation.main.favorite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.nqmgaming.furniture.R

@Composable
fun FavoriteScreen(navController: NavController) {
    Text(text = stringResource(id = R.string.favorites))

}