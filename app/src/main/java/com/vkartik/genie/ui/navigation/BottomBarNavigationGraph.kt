package com.vkartik.genie.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vkartik.genie.ui.shop.ShopDestination
import com.vkartik.genie.ui.shop.WishList

object BottomBarNavDestination {
    val route: String = "bottom_bar"
}


/**
 * Provides Navigation graph for the application.
 */
@Composable
fun BottomBarNavHost(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AccountsDestination.route,
        modifier = modifier
    ) {
        composable(route = AccountsDestination.route) {
            AccountsNavGraph(modifier = Modifier.fillMaxSize())
        }
        composable(route = ShopDestination.route) {
            WishList(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 4.dp),
            )
        }
    }
}