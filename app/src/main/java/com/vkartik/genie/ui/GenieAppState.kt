package com.vkartik.genie.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberGenieAppState(navController: NavHostController, coroutineScope: CoroutineScope): GenieAppState {
    return remember(navController, coroutineScope) {
        GenieAppState(navController, coroutineScope)
    }
}


@Stable
class GenieAppState(navController: NavHostController, coroutineScope: CoroutineScope) {
    
}