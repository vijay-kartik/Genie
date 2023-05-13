package com.vkartik.genie.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vkartik.genie.R
import com.vkartik.genie.ui.accounts.AccountEntryDestination
import com.vkartik.genie.ui.accounts.AccountEntryScreen
import com.vkartik.genie.ui.accounts.AccountsListDestination
import com.vkartik.genie.ui.accounts.AccountsListScreen

object AccountsDestination : NavigationDestination {
    override val route: String = "accounts"
    override val titleRes: Int = R.string.account
    override val icon: ImageVector = Icons.Default.AccountBox

}

@Composable
fun AccountsNavGraph(navHostController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navHostController,
        route = AccountsDestination.route,
        startDestination = AccountsListDestination.route
    ) {
        composable(route = AccountsListDestination.route) {
            AccountsListScreen(navigateToAccountEntry = {
                navHostController.navigate(
                    AccountEntryDestination.route
                )
            })
        }
        composable(route = AccountEntryDestination.route) {
            AccountEntryScreen(onNavigateUp = { navHostController.navigateUp() })
        }
    }
}