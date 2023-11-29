package com.example.jetcompose.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jetcompose.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreenView()
        }
    }
}

@Composable
private fun SplashScreenView(
) {
    val context = LocalContext.current as ComponentActivity
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                animationResId = R.raw.splash2,
                onComplete = {
                    val intent = Intent(context, BaseActivity::class.java)
                    context.startActivity(intent)
                    context.finish()
                }
            )
        }
    }
}

@Composable
fun LottieAnimation(
    animationResId: Int,
    onComplete: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationResId))
    LaunchedEffect(Unit) {
        delay(3000)
        onComplete()
    }
    LottieAnimation(
        composition = composition,
        modifier = Modifier.fillMaxSize(),
        speed = 0.5f,
        restartOnPlay = true,
        reverseOnRepeat = true,
        renderMode = RenderMode.AUTOMATIC,
        isPlaying = true
    )
}

