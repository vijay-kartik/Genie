package com.vkartik.genie.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

object OnBoardingDestination {
    val route: String = "OnBoarding"
}


@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState(initial = OnBoardingUiState(false))
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to Genie App!")
        if (!uiState.isRealUser) {
            Button(
                modifier = Modifier.padding(24.dp),
                onClick = onLoginClicked,
                colors = ButtonDefaults.filledTonalButtonColors()
            ) {
                Text(text = "Login")
            }
            Button(
                modifier = Modifier.padding(24.dp),
                onClick = onSignUpClicked,
                colors = ButtonDefaults.filledTonalButtonColors()
            ) {
                Text(text = "SignUp")
            }
        } else {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(24.dp),
                colors = ButtonDefaults.filledTonalButtonColors()
            ) {
                Text(text = "Sign Out")
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(24.dp),
                colors = ButtonDefaults.filledTonalButtonColors()
            ) {
                Text(text = "Delete Account")
            }
        }

    }
}