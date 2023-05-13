package com.vkartik.genie.ui.sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.makeitso.common.ext.basicButton
import com.example.makeitso.common.ext.fieldModifier
import com.vkartik.genie.R
import com.vkartik.genie.ui.components.BasicButton
import com.vkartik.genie.ui.components.BasicToolbar
import com.vkartik.genie.ui.components.EmailField
import com.vkartik.genie.ui.components.PasswordField
import com.vkartik.genie.ui.components.RepeatPasswordField

@Composable
fun SignUpScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    val fieldModifier = Modifier.fieldModifier()

    BasicToolbar(R.string.create_account)

    Column(
        modifier = modifier.fillMaxWidth().fillMaxHeight().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(uiState.email, viewModel::onEmailChange, fieldModifier)
        PasswordField(uiState.password, viewModel::onPasswordChange, fieldModifier)
        RepeatPasswordField(uiState.repeatPassword, viewModel::onRepeatPasswordChange, fieldModifier)

        BasicButton(R.string.create_account, Modifier.basicButton()) {
            viewModel.onSignUpClick(openAndPopUp)
        }
    }
}