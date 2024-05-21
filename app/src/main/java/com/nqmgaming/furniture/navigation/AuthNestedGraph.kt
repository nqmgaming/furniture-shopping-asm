package com.nqmgaming.furniture.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.nqmgaming.furniture.presentation.Screen
import com.nqmgaming.furniture.presentation.authentication.login.LoginScreen
import com.nqmgaming.furniture.presentation.authentication.onboarding.OnboardingScreen
import com.nqmgaming.furniture.presentation.authentication.signup.SignUpScreen
import com.nqmgaming.furniture.presentation.authentication.splash.SplashScreen

fun NavGraphBuilder.authGraph(
    navController: NavController
){
    navigation(
        startDestination = Screen.OnboardingScreen.route,
        route = Screen.AuthRoute.route
    ){
        composable(
            Screen.OnboardingScreen.route,
//                            enterTransition = {
//                                return@composable fadeIn(tween(1000))
//                            },
//                            exitTransition = {
//                                return@composable fadeOut(tween(1000))
//                            }
        ) {
            OnboardingScreen(navController)
        }
        composable(
            Screen.LoginScreen.route,
//                            enterTransition = {
//                                return@composable scaleIn(
//                                    spring(
//                                        Spring.DampingRatioHighBouncy,
//                                        stiffness = Spring.StiffnessLow
//                                    )
//                                )
//                            },
//                            exitTransition = {
//                                return@composable scaleOut(
//                                    spring(
//                                        Spring.DampingRatioHighBouncy,
//                                        stiffness = Spring.StiffnessLow
//                                    )
//                                )
//                            }
        ) {
            LoginScreen(navController)
        }
        composable(
            Screen.SignUpScreen.route,
//                            enterTransition = {
//                                return@composable slideIntoContainer(
//                                    AnimatedContentTransitionScope.SlideDirection.End,
//                                    tween(700)
//                                )
//                            },
//                            exitTransition = {
//                                return@composable slideOutOfContainer(
//                                    AnimatedContentTransitionScope.SlideDirection.Start,
//                                    tween(700)
//                                )
//                            },
        ) {
            SignUpScreen(navController)
        }
        composable(
            Screen.SplashScreen.route,
//                            enterTransition = {
//                                expandIn(tween(700, easing = LinearEasing))
//                            },
//                            exitTransition = {
//                                shrinkOut(
//                                    tween(700, easing = LinearEasing)
//                                )
//
//                            }
        ) {
            SplashScreen(navController = navController)
        }
    }

}