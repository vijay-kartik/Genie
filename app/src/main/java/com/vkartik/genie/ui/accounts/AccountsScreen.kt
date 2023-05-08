package com.vkartik.genie.ui.accounts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vkartik.genie.R
import com.vkartik.genie.ui.AppViewModelProvider
import com.vkartik.genie.ui.components.SearchBar
import com.vkartik.genie.ui.navigation.NavigationDestination

object AccountsDestination : NavigationDestination {
    override val route: String = "accounts"
    override val titleRes: Int = R.string.account
    override val icon: ImageVector = Icons.Filled.AccountBox
}

@Composable
fun AccountsScreen(
    navigateToAccountEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AccountsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = navigateToAccountEntry,
            modifier = Modifier.navigationBarsPadding()
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_account),
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }) { innerPadding ->
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .heightIn(56.dp)
                .padding(4.dp)
        )
    }

}