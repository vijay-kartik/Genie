package com.vkartik.genie.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

interface BottomBarDestination {
    val route: String
    val titleRes: Int
    val icon: ImageVector
}