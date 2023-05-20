package com.vkartik.genie.ui.accounts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vkartik.genie.R
import com.vkartik.genie.domain.model.Account
import com.vkartik.genie.ui.components.SearchBar
import com.vkartik.genie.ui.navigation.BottomBarDestination

object AccountsListDestination : BottomBarDestination {
    override val route: String = "list"
    override val titleRes: Int = R.string.add_account
    override val icon: ImageVector = Icons.Filled.AccountBox
}

@Composable
fun AccountsListScreen(
    navigateToAccountEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AccountsViewModel = hiltViewModel()
) {
    val accountsUiState: AccountsUiState by viewModel.accountsUiState.collectAsState()
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
    }, modifier = modifier) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(56.dp)
                    .padding(4.dp)
            )
            AccountList(
                accountList = accountsUiState.accountsList,
                onItemClick = { TODO() },
                modifier = Modifier
            )
        }

    }
}

@Composable
fun AccountList(
    modifier: Modifier = Modifier,
    accountList: List<Account>,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (accountList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_account_description),
                style = MaterialTheme.typography.subtitle2
            )
        } else {
            LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(items = accountList, key = { it.name }) { account ->
                    AccountEntry(account = account, onAccountClick = { onItemClick(it.name) })
                }
            }
        }
    }
}

@Composable
fun AccountEntry(
    account: Account,
    onAccountClick: (Account) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onAccountClick(account) }
        .padding(vertical = 16.dp)
    ) {
        Text(
            text = account.name,
            modifier = Modifier.weight(1.5f),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = account.password,
            modifier = Modifier.weight(1.0f)
        )
        Text(text = account.userName, modifier = Modifier.weight(1.0f))
    }
}