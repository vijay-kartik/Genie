package com.vkartik.genie.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.makeitso.common.ext.basicButton
import com.example.makeitso.common.ext.fieldModifier
import com.example.makeitso.common.ext.textButton
import com.vkartik.genie.R
import com.vkartik.genie.ui.components.BasicButton
import com.vkartik.genie.ui.components.BasicTextButton
import com.vkartik.genie.ui.components.EmailField
import com.vkartik.genie.ui.components.PasswordField

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    TopAppBar(title = { Text(stringResource(R.string.login_details)) }, backgroundColor = MaterialTheme.colors.secondary)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(uiState.email, viewModel::onEmailChange, Modifier.fieldModifier())
        PasswordField(uiState.password, viewModel::onPasswordChange, Modifier.fieldModifier())

        BasicButton(R.string.sign_in, Modifier.basicButton()) { viewModel.onSignInClick(openAndPopUp) }

        BasicTextButton(R.string.forgot_password, Modifier.textButton()) {
            viewModel.onForgotPasswordClick()
        }
    }
}