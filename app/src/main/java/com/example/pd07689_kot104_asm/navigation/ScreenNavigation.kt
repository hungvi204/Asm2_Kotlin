package com.example.pd07689_kot104_asm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pd07689_kot104_asm.ui.screen.Boarding
import com.example.pd07689_kot104_asm.ui.screen.ProductDetail
import com.example.pd07689_kot104_asm.ui.screen.Login
import com.example.pd07689_kot104_asm.ui.screen.Cart
import com.example.pd07689_kot104_asm.ui.screen.CheckOut
import com.example.pd07689_kot104_asm.ui.screen.Congrats
import com.example.pd07689_kot104_asm.ui.screen.Home
import com.example.pd07689_kot104_asm.ui.screen.SignUp

@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Boarding.route,
    ) {
        composable(Screen.Boarding.route) { Boarding(navController) }
        composable(Screen.Login.route) { Login(navController) }
        composable(Screen.SignUp.route) { SignUp(navController) }
        composable(Screen.MyBottombar.route) { MyBottombar(navController) }
        composable(Screen.Home.route) { Home(navController) }
        composable(
            "${Screen.ProductDetail.route}/{productID}",
            arguments = listOf(navArgument("productID") { type = NavType.StringType })
        ) {
            ProductDetail(
                productId = it.arguments?.getString("productID").orEmpty(),
                navController = navController
            )
        }
        composable(Screen.Cart.route) { Cart(navController) }
        composable(Screen.CheckOut.route) { CheckOut(navController) }
        composable(Screen.Congrats.route) { Congrats(navController) }
    }
}