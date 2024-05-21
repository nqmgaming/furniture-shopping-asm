package com.nqmgaming.furniture.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.nqmgaming.furniture.presentation.Screen
import com.nqmgaming.furniture.presentation.authentication.splash.SplashScreen
import com.nqmgaming.furniture.presentation.main.MainScreen
import com.nqmgaming.furniture.presentation.main.favorite.FavoriteScreen
import com.nqmgaming.furniture.presentation.main.home.HomeScreen
import com.nqmgaming.furniture.presentation.main.notification.NotificationScreen
import com.nqmgaming.furniture.presentation.main.profile.ProfileScreen

fun NavGraphBuilder.appGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.SplashScreen.route,
        route = Screen.AppRoute.route
    ) {
        composable(
            Screen.SplashScreen.route
        ) {
            SplashScreen(navController)
        }
        composable(
            Screen.MainScreen.route
        ) {
            MainScreen()
        }
        composable(
            Screen.HomeScreen.route
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            Screen.FavoritesScreen.route
        ) {
            FavoriteScreen(navController = navController)
        }
        composable(
            Screen.ProfileScreen.route
        ) {
            ProfileScreen(navController = navController)
        }
        composable(
            Screen.NotificationsScreen.route
        ) {
            NotificationScreen(navController = navController)
        }
    }
}