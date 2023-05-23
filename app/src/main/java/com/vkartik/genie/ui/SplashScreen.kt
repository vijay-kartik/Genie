package com.vkartik.genie.ui

import android.window.SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vkartik.genie.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToOnBoarding: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_anim))
    val progress by animateLottieCompositionAsState(composition)

    LottieAnimation(
        composition,
        modifier = Modifier.fillMaxSize(),
        progress = { progress },
    )

    LaunchedEffect(key1 = true) {
        delay(2000L)
        navigateToOnBoarding()
    }
}