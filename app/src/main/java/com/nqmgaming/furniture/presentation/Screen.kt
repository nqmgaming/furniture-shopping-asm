package com.nqmgaming.furniture.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.nqmgaming.furniture.R

sealed class Screen(val route: String) {
    data object AuthRoute : Screen(route = "Auth")
    data object OnboardingScreen : Screen(route = "onboarding")
    data object LoginScreen : Screen(route = "login")
    data object SignUpScreen : Screen(route = "signup")
    data object SplashScreen : Screen(route = "splash")
    data object AppRoute : Screen(route = "App")
    data object MainScreen : Screen(route = "main")
    data object HomeScreen : Screen(route = "home")
    data object ProductDetailScreen : Screen(route = "productDetail")
    data object FavoritesScreen : Screen(route = "favorites")
    data object NotificationsScreen : Screen(route = "notifications")
    data object ProfileScreen : Screen(route = "profile")
    data object CartScreen : Screen(route = "cart")
    data object CheckoutScreen : Screen(route = "checkout")
    data object CheckoutSuccessScreen : Screen(route = "checkout_success")
}