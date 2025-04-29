package com.example.cheerup

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.cheerup.ui.screens.AuthScreen  // Добавьте этот импорт
import com.example.cheerup.ui.theme.CheerUpTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
            setOnExitAnimationListener { screen ->
                ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                ).apply {
                    interpolator = OvershootInterpolator()
                    duration = 500L
                    doOnEnd { screen.remove() }
                }.start()

                ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4f,
                    0.0f
                ).apply {
                    interpolator = OvershootInterpolator()
                    duration = 500L
                    doOnEnd { screen.remove() }
                }.start()
            }
        }

        setContent {
            CheerUpTheme {
                AuthScreenWrapper()
            }
        }
    }
}

@Composable
fun AuthScreenWrapper() {
    AuthScreen()
}