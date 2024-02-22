package com.example.orders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.OrdersTheme
import com.example.orders.presenter.pages.HomePage
import com.example.orders.presenter.pages.HomePage2
import com.example.orders.presenter.pages.ProductDetail

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OrdersTheme(useDarkTheme = true) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        HomePage2(navController)
                    }
                    composable(
                        "productDetail/{productId}/{sectionId}",
                        arguments = listOf(navArgument("productId") {
                            type = NavType.StringType
                        }, navArgument("sectionId") {
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getString("productId")
                        val sectionId = backStackEntry.arguments?.getString("sectionId")

                        ProductDetail(navController, productId!!,sectionId!!)
                    }
                }
            }

        }
    }
}

@Composable
fun App() {
    HomePage2()
}


@Preview(showSystemUi = true)
@Composable
private fun AppPreview() {
    OrdersTheme(useDarkTheme = true) {
        App()
    }
}