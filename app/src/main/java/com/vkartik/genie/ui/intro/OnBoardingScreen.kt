package com.vkartik.genie.ui.intro

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navigateToLogin: () -> Unit) {
    val onBoardingScreens = listOf(
        OnBoardingData("Welcome to SecureStore!", "SecureStore is your personal vault for storing and managing all your passwords in one place."),
        OnBoardingData("Strong Encryption", "Your passwords are stored with strong encryption. Only you can access them."),
        OnBoardingData("Generate Passwords", "Struggling to come up with strong passwords? Our built-in password generator can help."),
        OnBoardingData("Easy Access", "Quickly and easily copy your passwords when you need them.")
    )

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    HorizontalPager(pageCount = onBoardingScreens.size, state = pagerState) { page ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = onBoardingScreens[page].title,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = onBoardingScreens[page].description,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    if (page == onBoardingScreens.lastIndex) {
                        navigateToLogin()
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(page + 1)
                        }
                    }
                }
            ) {
                Text(if (page == onBoardingScreens.lastIndex) "Get Started" else "Next")
            }
        }
    }
}