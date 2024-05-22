package com.nqmgaming.furniture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nqmgaming.furniture.navigation.appGraph
import com.nqmgaming.furniture.navigation.authGraph
import com.nqmgaming.furniture.presentation.Screen
import com.nqmgaming.furniture.ui.theme.FurnitureShoppingTheme
import com.nqmgaming.furniture.util.SharedPrefUtils
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.SupabaseClient
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var supabaseClient: SupabaseClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FurnitureShoppingTheme {
                val isLogin = SharedPrefUtils.getBoolean(this@MainActivity, "isLogin", false)
                val starrDestination = if (isLogin) {
                    Screen.AppRoute.route
                } else {
                    Screen.AuthRoute.route
                }
                Surface {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = starrDestination
                    ) {
                        authGraph(navController = navController)
                        appGraph(navController = navController)
                    }
                }
            }
        }




    }
}
