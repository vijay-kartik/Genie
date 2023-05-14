package com.vkartik.genie

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vkartik.genie.ui.OnBoardingScreen
import com.vkartik.genie.ui.navigation.AccountsDestination
import com.vkartik.genie.ui.navigation.BottomBarNavHost
import com.vkartik.genie.ui.shop.ShopDestination
import com.vkartik.genie.ui.theme.GenieTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.onEach {
                    uiState = it
                }.collect()
            }
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                MainActivityUiState.Loading -> true
                is MainActivityUiState.Success -> false
            }
        }

        setContent {
            GenieTheme {
                MyApp()
            }
        }
    }

    @Composable
    private fun MyApp() {
        var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }
        val navController = rememberNavController()
        val items = listOf(
            AccountsDestination,
            ShopDestination
        )
        if (shouldShowOnBoarding) {
            OnBoardingScreen(onLoginClicked = {}, onSignUpClicked = {})
        } else {
            Scaffold(bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { destination ->
                        NavigationBarItem(icon = {
                            Icon(
                                destination.icon, contentDescription = null
                            )
                        },
                            label = { Text(text = stringResource(id = destination.titleRes)) },
                            selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
                            onClick = {
                                navController.navigate(destination.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            })
                    }
                }
            }) { innerPadding ->
                BottomBarNavHost(navController = navController, Modifier.padding(innerPadding))
            }
        }
    }

    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
    @Composable
    fun DefaultPreview() {
        GenieTheme {
            MyApp()
        }
    }
}