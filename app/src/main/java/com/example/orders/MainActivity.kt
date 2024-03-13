package com.example.orders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.OrdersTheme
import com.example.orders.presenter.pages.CartPage
import com.example.orders.presenter.pages.HomePage2
import com.example.orders.presenter.pages.ProductDetail
import com.example.orders.presenter.viewModel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val homeViewModel by viewModels<HomeViewModel>()
        setContent {
            OrdersTheme(useDarkTheme = true) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        HomePage2(navController, homeViewModel, navigateProduct = {


                        })
                    }
                    composable(
                        "${Route.productDetailScreen}/{productId}/{sectionId}",
                        arguments = listOf(navArgument("productId") {
                            type = NavType.StringType
                        }, navArgument("sectionId") {
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getString("productId")
                        val sectionId = backStackEntry.arguments?.getString("sectionId")

                        ProductDetail(
                            navController,
                            productId!!,
                            sectionId!!,
                        )
                    }
                    composable("cart") {
                        CartPage(navController)
                    }
                }
            }

        }
    }
}

object Route {
    const val mainScreen = "main"
    const val productDetailScreen = "productDetail"


}



