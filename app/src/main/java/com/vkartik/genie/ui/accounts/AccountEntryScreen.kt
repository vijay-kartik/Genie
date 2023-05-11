package com.vkartik.genie.ui.accounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vkartik.genie.R
import com.vkartik.genie.ui.AppViewModelProvider
import com.vkartik.genie.ui.navigation.NavigationDestination
import com.vkartik.genie.ui.theme.GenieTheme
import kotlinx.coroutines.launch

object AccountEntryDestination : NavigationDestination {
    override val route: String = "entry"
    override val titleRes: Int = R.string.add_account
    override val icon: ImageVector = Icons.Default.Star
    const val accountIdArg = "accountId"
    val routeWithArgs = "$route/{$accountIdArg}"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountEntryScreen(
    onNavigateUp: () -> Unit,
    viewModel: AccountEntryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(AccountsListDestination.titleRes)) },
            modifier = modifier,
            navigationIcon = {
                IconButton(onClick = onNavigateUp) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            })
    }) { innerPadding ->
        AccountEntryBody(
            accountUiState = viewModel.accountUiState,
            onAccountValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveAccount(onNavigateUp)
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AccountEntryBody(
    accountUiState: AccountUiState,
    onAccountValueChange: (AccountUiState) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        AccountInputForm(accountUiState = accountUiState, onValueChange = onAccountValueChange)
        Button(
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth(),
            content = {
                Text(
                    text = stringResource(
                        id = R.string.save
                    )
                )
            })
    }
}

@Composable
fun AccountInputForm(
    accountUiState: AccountUiState,
    onValueChange: (AccountUiState) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = accountUiState.name,
            onValueChange = { onValueChange(accountUiState.copy(name = it)) },
            label = { Text(text = stringResource(id = R.string.account_name)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = accountUiState.userName,
            onValueChange = { onValueChange(accountUiState.copy(userName = it)) },
            label = { Text(text = stringResource(id = R.string.user_name)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = accountUiState.password,
            onValueChange = { onValueChange(accountUiState.copy(password = it)) },
            label = { Text(text = stringResource(id = R.string.password)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemEntryScreenPreview() {
    GenieTheme {
        AccountEntryBody(
            accountUiState = AccountUiState(
                name = "Account name",
                userName = "user name",
                password = "password"
            ),
            onAccountValueChange = {},
            onSaveClick = {}
        )
    }
}