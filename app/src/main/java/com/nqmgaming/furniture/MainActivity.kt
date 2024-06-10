package com.nqmgaming.furniture

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nqmgaming.furniture.presentation.Screen
import com.nqmgaming.furniture.presentation.authentication.login.LoginScreen
import com.nqmgaming.furniture.presentation.authentication.onboarding.OnboardingScreen
import com.nqmgaming.furniture.presentation.authentication.signup.SignUpScreen
import com.nqmgaming.furniture.presentation.authentication.splash.SplashScreen
import com.nqmgaming.furniture.presentation.main.cart.CartScreen
import com.nqmgaming.furniture.presentation.main.checkout.CheckoutScreen
import com.nqmgaming.furniture.presentation.main.favorite.FavoriteScreen
import com.nqmgaming.furniture.presentation.main.home.HomeScreen
import com.nqmgaming.furniture.presentation.main.notification.NotificationScreen
import com.nqmgaming.furniture.presentation.main.productDetail.ProductDetailScreen
import com.nqmgaming.furniture.presentation.main.profile.ProfileScreen
import com.nqmgaming.furniture.ui.theme.FurnitureShoppingTheme
import com.nqmgaming.furniture.util.SharedPrefUtils
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.SupabaseClient
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var supabaseClient: SupabaseClient
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FurnitureShoppingTheme {
                val isLogin = SharedPrefUtils.getBoolean(this@MainActivity, "isLogin", false)
                SharedPrefUtils.saveBoolean(this@MainActivity, "isFirst", true)
                val starrDestination = if (isLogin) {
                    Screen.SplashScreen.route
                } else {
                    Screen.OnboardingScreen.route
                }

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val items = listOf(
                    Screen.HomeScreen.route,
                    Screen.FavoritesScreen.route,
                    Screen.NotificationsScreen.route,
                    Screen.ProfileScreen.route
                )
                val currentRoute = currentDestination?.route
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentRoute in items) {
                            BottomNavAnimation(
                                screens = BottomNavigationItem().bottomNavigationItems(),
                                navController = navController
                            )
                        }
                    }
                ) { paddingValues ->
                    SharedTransitionLayout {
                        NavHost(
                            navController = navController,
                            startDestination = starrDestination,
                            modifier = Modifier.padding(paddingValues),
                        ) {
                            composable(
                                Screen.SplashScreen.route,
                                enterTransition = {
                                    return@composable scaleIn(
                                        animationSpec = tween(1000),
                                        initialScale = 0.5f
                                    )
                                }
                            ) {
                                SplashScreen(navController)
                            }
                            composable(
                                Screen.OnboardingScreen.route,
                                enterTransition = {
                                    return@composable slideInHorizontally(
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioMediumBouncy,
                                            stiffness = Spring.StiffnessLow
                                        ),
                                        initialOffsetX = { -it }
                                    )
                                },
                                exitTransition = {
                                    return@composable fadeOut(tween(1000))
                                }
                            ) {
                                OnboardingScreen(navController)
                            }
                            composable(Screen.HomeScreen.route,
                                enterTransition = {
                                    fadeIn(animationSpec = tween(300))
                                },
                                exitTransition = {
                                    fadeOut(animationSpec = tween(300))
                                }
                            ) {
                                HomeScreen(
                                    navController
                                )
                            }
                            composable(
                                Screen.LoginScreen.route,
                            ) {
                                LoginScreen(navController)
                            }
                            composable(
                                Screen.SignUpScreen.route,
                            ) {
                                SignUpScreen(navController)
                            }
                            composable(Screen.FavoritesScreen.route, enterTransition = {
                                fadeIn(animationSpec = tween(300))
                            },
                                exitTransition = {
                                    fadeOut(animationSpec = tween(300))
                                }) {
                                FavoriteScreen(
                                    navController
                                )
                            }
                            composable(Screen.NotificationsScreen.route, enterTransition = {
                                fadeIn(animationSpec = tween(300))
                            },
                                exitTransition = {
                                    fadeOut(animationSpec = tween(300))
                                }) {
                                NotificationScreen(
                                    navController
                                )
                            }
                            composable(Screen.ProfileScreen.route, enterTransition = {
                                fadeIn(animationSpec = tween(300))
                            },
                                exitTransition = {
                                    fadeOut(animationSpec = tween(300))
                                }) {
                                ProfileScreen(
                                    navController
                                )
                            }
                            composable(
                                Screen.ProductDetailScreen.route + "/{productId}",
                                arguments = listOf(
                                    navArgument("productId") {
                                        type = NavType.IntType
                                    }
                                ),
                                enterTransition = {
                                    fadeIn(animationSpec = tween(300))
                                },
                                exitTransition = {
                                    fadeOut(animationSpec = tween(300))
                                }
                            ) {
                                val productId = it.arguments?.getInt("productId")
                                Log.d(
                                    "Navigation",
                                    "Navigating to ${Screen.ProductDetailScreen.route}/$productId"
                                )
                                ProductDetailScreen(
                                    navController = navController
                                )
                            }
                            composable(
                                route = Screen.CartScreen.route,
                                enterTransition = {
                                    fadeIn(animationSpec = tween(300))
                                },
                                exitTransition = {
                                    fadeOut(animationSpec = tween(300))
                                }
                            ) {
                                CartScreen(
                                    navController = navController
                                )
                            }

                            composable(
                                route = Screen.CheckoutScreen.route + "/{total}",
                                arguments = listOf(
                                    navArgument("total") {
                                        type = NavType.FloatType
                                    }
                                ),
                                enterTransition = {
                                    fadeIn(animationSpec = tween(300))
                                },
                                exitTransition = {
                                    fadeOut(animationSpec = tween(300))
                                }
                            ) {
                                val totalPrice = it.arguments?.getFloat("total")
                                Log.d(
                                    "Navigation",
                                    "Navigating to ${Screen.CheckoutScreen.route}/$totalPrice"
                                )
                                CheckoutScreen(navController = navController)
                            }

                        }
                    }
                }
            }
        }
    }
}

data class BottomNavigationItem(
    val label: String = "",
    val route: String = "",
    val selectedIcon: ImageVector = Icons.Filled.Home,
    val unselectedIcon: ImageVector = Icons.Outlined.Home
) {
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                route = Screen.HomeScreen.route,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
            BottomNavigationItem(
                label = "Favorites",
                route = Screen.FavoritesScreen.route,
                selectedIcon = Icons.Filled.Favorite,
                unselectedIcon = Icons.Outlined.FavoriteBorder
            ),
            BottomNavigationItem(
                label = "Alerts",
                route = Screen.NotificationsScreen.route,
                selectedIcon = Icons.Filled.Notifications,
                unselectedIcon = Icons.Outlined.Notifications
            ),
            BottomNavigationItem(
                label = "Profile",
                route = Screen.ProfileScreen.route,
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person
            )
        )
    }
}

@Composable
fun BottomNavAnimation(
    screens: List<BottomNavigationItem>,
    navController: NavController = rememberNavController()
) {
    var selectedScreen by remember { mutableIntStateOf(0) }
    Box(
        Modifier
            .shadow(5.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .height(64.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (screen in screens) {
                val isSelected = screen == screens[selectedScreen]
                val animatedWeight by animateFloatAsState(targetValue = if (isSelected) 1.5f else 1f)
                Box(
                    modifier = Modifier
                        .weight(animatedWeight),
                    contentAlignment = Alignment.Center,
                ) {
                    val interactionSource = remember { MutableInteractionSource() }
                    BottomNavItem(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                        ) {
                            selectedScreen = screens.indexOf(screen)
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        screen = screen,
                        isSelected = isSelected,
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavItem(
    modifier: Modifier = Modifier,
    screen: BottomNavigationItem,
    isSelected: Boolean,
) {
    val animatedHeight by animateDpAsState(targetValue = if (isSelected) 36.dp else 26.dp)
    val animatedElevation by animateDpAsState(targetValue = if (isSelected) 15.dp else 0.dp)
    val animatedAlpha by animateFloatAsState(targetValue = if (isSelected) 1f else .5f)
    val animatedIconSize by animateDpAsState(
        targetValue = if (isSelected) 26.dp else 20.dp,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .height(animatedHeight)
                .shadow(
                    elevation = animatedElevation,
                    shape = RoundedCornerShape(20.dp)
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                rememberVectorPainter(
                    image = if (isSelected) screen.selectedIcon else screen.unselectedIcon
                ),
                contentDescription = screen.label,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxHeight()
                    .padding(start = 11.dp)
                    .alpha(animatedAlpha)
                    .size(animatedIconSize)

            )

            if (isSelected) {
                Text(
                    text = screen.label,
                    modifier = Modifier.padding(start = 8.dp, end = 10.dp),
                    maxLines = 1,
                )
            }
        }
    }
}
