package com.vkartik.genie.ui.accounts

import android.content.ClipData
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
    val scaffoldState = rememberScaffoldState()
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = navigateToAccountEntry, modifier = Modifier.navigationBarsPadding()
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_account),
                tint = MaterialTheme.colors.onPrimary
            )
        }
    },
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = scaffoldState.snackbarHostState) }) { innerPadding ->
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
                viewModel = viewModel,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun AccountList(
    modifier: Modifier = Modifier, accountList: List<Account>, viewModel: AccountsViewModel
) {
    val context = LocalContext.current
    viewModel.setCopyToClipboardCallback { clipBoardData ->
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clipData = ClipData.newPlainText("Account Details", clipBoardData)
        clipboardManager.setPrimaryClip(clipData)
    }
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
            val showDeleteDialog =
                rememberUpdatedState(newValue = viewModel.showDeleteConfirmationDialog.value)
            LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(items = accountList, key = { it.name }) { account ->
                    AccountEntry(account = account,
                        onDeleteClick = { viewModel.performDeleteAction(account) },
                        onCopyClick = { viewModel.copyAccountDetailsToClipboard(account) })
                    if (showDeleteDialog.value == true) {
                        Log.e("kartikk", "show dialog")
                        DeleteAccountConfirmationDialog(onDismiss = { viewModel.onDeleteDialogDismissed() },
                            onConfirm = { viewModel.performDeleteAction(account) })
                    }
                }
            }
        }
    }


}

@Composable
fun AccountEntry(
    account: Account,
    onDeleteClick: () -> Unit,
    onCopyClick: (Account) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(60.dp), colors = CardDefaults.cardColors()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.gifts),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(44.dp)
                    .clip(CircleShape)
                    .border(width = 5.dp, shape = CircleShape, color = Color.Transparent),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = account.name, fontWeight = FontWeight.Bold)
                Text(text = account.userName, maxLines = 1)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(painterResource(id = R.drawable.ic_content_copy),
                contentDescription = "Copy",
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp)
                    .clickable { onCopyClick(account) })
            Icon(imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp)
                    .clickable { onDeleteClick() })
        }
    }

}

@Composable
fun DeleteAccountConfirmationDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(onDismissRequest = onDismiss,
        title = { Text("Confirmation") },
        text = { Text("Are you sure you want to perform this action?") },
        confirmButton = {
            Button(onClick = {
                onConfirm()
                onDismiss()
            }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        })
}