package com.vkartik.genie.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vkartik.genie.R
import com.vkartik.genie.ui.accounts.AccountEntryDestination
import com.vkartik.genie.ui.accounts.AccountEntryScreen
import com.vkartik.genie.ui.accounts.AccountsListDestination
import com.vkartik.genie.ui.accounts.AccountsListScreen

object AccountsDestination : BottomBarDestination {
    override val route: String = "accounts"
    override val titleRes: Int = R.string.account
    override val icon: ImageVector = Icons.Default.AccountBox

}

@Composable
fun AccountsNavGraph(navHostController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
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
            }, modifier = modifier)
        }
        composable(route = AccountEntryDestination.route) {
            AccountEntryScreen(onNavigateUp = { navHostController.navigateUp() })
        }
    }
}