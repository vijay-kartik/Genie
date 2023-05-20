package com.vkartik.genie.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vkartik.genie.ui.settings.OnBoardingDestination
import com.vkartik.genie.ui.settings.SettingsScreen
import com.vkartik.genie.ui.login.LoginDestination
import com.vkartik.genie.ui.login.LoginScreen
import com.vkartik.genie.ui.sign_up.SignUpDestination
import com.vkartik.genie.ui.sign_up.SignUpScreen

object OnBoardingNavDestination {
    val route: String = "OnBoardingRoot"
}

fun NavGraphBuilder.onBoardingGraph(navController: NavHostController) {
    navigation(route = OnBoardingNavDestination.route, startDestination = OnBoardingDestination.route) {
        composable(OnBoardingDestination.route) {
            SettingsScreen(onLoginClicked = { navController.navigate(LoginDestination.route) },
                onSignUpClicked = {
                    navController.navigate(SignUpDestination.route) {
                        popUpTo(OnBoardingDestination.route) { inclusive = true }
                    }
                })
        }
        composable(SignUpDestination.route) {
            SignUpScreen(openAndPopUp = { route, popUp ->
                navController.navigate(route) {
                    popUpTo(
                        popUp
                    ) { inclusive = true }
                }
            })
        }
        composable(LoginDestination.route) {
            LoginScreen(openAndPopUp = { route, popUp ->
                navController.navigate(route) {
                    popUpTo(
                        popUp
                    ) { inclusive = true }
                }
            })
        }
    }
}