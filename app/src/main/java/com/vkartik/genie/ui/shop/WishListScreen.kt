package com.vkartik.genie.ui.shop

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.vkartik.genie.R
import com.vkartik.genie.ui.navigation.NavigationDestination

object ShopDestination: NavigationDestination {
    override val route: String = "shop"
    override val titleRes: Int = R.string.shop
    override val icon: ImageVector = Icons.Default.ShoppingCart
}

@Composable
fun WishList(modifier: Modifier = Modifier, navController: NavController) {
    val itemList = listOf("Food", "Electronics", "Body Care")
    LazyColumn(
        modifier = modifier
    ) {
        items(items = itemList) {item ->
            TextItem(item)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextItem(name: String, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    
    val extraPadding by animateDpAsState(
        targetValue = if(expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Card(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Text(name,
                modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp)), style = MaterialTheme.typography.headlineMedium)
            ElevatedButton(onClick = { expanded = !expanded }) {
                Text(text = if(expanded) "Show Less" else "Show More")
            }
        }
    }
}