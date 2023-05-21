package com.vkartik.genie.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vkartik.genie.ui.shop.ShopDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenieNavHost(navController: NavHostController, modifier: Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentBackStackEntry = navBackStackEntry?.destination
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { currentBackStackEntry?.route?.let { Text(text = it) } },
            actions = {
                IconButton(onClick = { navController.navigate(SettingsNavDestination.route) }) {
                    Icon(Icons.Default.Settings, contentDescription = null)

                }
            })
    }) {
        innerPadding ->
        NavHost(navController = navController, startDestination = BottomBarNavDestination.route) {
            composable(route = BottomBarNavDestination.route) {
                HomeScreen(modifier = modifier.padding(innerPadding))
            }
            settingsGraph(navController)
        }
    }

}

@Composable
fun HomeScreen(modifier: Modifier) {
    val bottomBarItems = listOf(AccountsDestination, ShopDestination)
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentBackStackEntry = navBackStackEntry?.destination
    Scaffold(bottomBar = {
        NavigationBar {
            bottomBarItems.forEach { screen ->
                NavigationBarItem(selected = currentBackStackEntry?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = { navController.navigate(screen.route) },
                    icon = { Icon(screen.icon, null) },
                    label = { Text(screen.route) })
            }
        }
    }) { innerPadding ->
        BottomBarNavHost(
            navController = navController, modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }

}