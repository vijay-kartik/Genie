package com.vkartik.genie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vkartik.genie.ui.OnBoardingDestination
import com.vkartik.genie.ui.OnBoardingScreen
import com.vkartik.genie.ui.login.LoginDestination
import com.vkartik.genie.ui.login.LoginScreen
import com.vkartik.genie.ui.sign_up.SignUpDestination

object OnBoardingNavDestination {
    val route: String = "OnBoardingRoot"
}

@Composable
fun OnBoardingNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = OnBoardingNavDestination.route,
        startDestination = OnBoardingDestination.route
    ) {
        composable(OnBoardingDestination.route) {
            OnBoardingScreen(onLoginClicked = { navController.navigate(LoginDestination.route) },
                onSignUpClicked = { navController.navigate(SignUpDestination.route) })
        }
    }
}