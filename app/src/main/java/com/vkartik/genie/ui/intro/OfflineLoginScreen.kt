package com.vkartik.genie.ui.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.vkartik.genie.R
import com.vkartik.genie.ui.utils.showBiometricPrompt

@Composable
fun OfflineLoginScreen(
    navigateToHome: () -> Unit,
    viewModel: OfflineLoginViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var shouldAuthenticateWithBiometricPrompt =
        rememberSaveable { viewModel.isBiometricEnabled.value }
    var masterKey = remember { mutableStateOf("") }
    var passwordVisibility = remember {
        mutableStateOf(false)
    }

    val visibilityIcon = painterResource(R.drawable.ic_visibility_on)
    val visibilityOffIcon = painterResource(R.drawable.ic_visibility_off)

    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (shouldAuthenticateWithBiometricPrompt) {
            showBiometricPrompt(context, context as FragmentActivity) {
                navigateToHome()
            }
        } else {
            Text(
                text = "Enter your master key to login",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            OutlinedTextField(
                value = masterKey.value,
                onValueChange = {
                    masterKey.value = it
                },
                label = { Text("Master Key") },
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                        Icon(
                            painter = if (passwordVisibility.value) visibilityIcon else visibilityOffIcon,
                            contentDescription = if (passwordVisibility.value) "Hide password" else "Show password"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    if (viewModel.isLoginDetailsCorrect(masterKey.value)) {
                        navigateToHome()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }
    }
}