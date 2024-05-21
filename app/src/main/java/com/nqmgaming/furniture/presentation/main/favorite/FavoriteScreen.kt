package com.nqmgaming.furniture.presentation.main.favorite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nqmgaming.furniture.R

@Composable
fun FavoriteScreen(navController: NavController) {
    Text(text = stringResource(id = R.string.favorites))
}

@Preview
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen(navController =rememberNavController())
}