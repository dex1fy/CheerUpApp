package com.example.cheerup

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.cheerup.ui.theme.CheerUpTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cheerup.data.models.UserState
import com.example.cheerup.ui.navigation.Navigation
import com.example.cheerup.ui.navigation.NavigationRoutes
import io.ktor.websocket.Frame

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
                    screen.iconView, View.SCALE_X, 0.4f, 0.0f
                ).apply {
                    interpolator = OvershootInterpolator()
                    duration = 500L
                    doOnEnd { screen.remove() }
                }.start()

                ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_Y, 0.4f, 0.0f
                ).apply {
                    interpolator = OvershootInterpolator()
                    duration = 500L
                    doOnEnd { screen.remove() }
                }.start()
            }
        }

        setContent {
            CheerUpTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }

            }
        }


    }

}

@Composable
fun MainScreen(
    viewModel: SupabaseAuthViewModel = viewModel(), navController: NavController
) {
    val context = LocalContext.current
    val userState by viewModel.userState

    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    var currentUserState by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.isUserLoggedIn(
            context,
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        TextField(value = userEmail, placeholder = {
            Text(text = "Введите почту")
        }, onValueChange = {
            userEmail = it
        })
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(value = userPassword, placeholder = {
            Text(text = "Введите пароль")
        }, onValueChange = {
            userPassword = it
        })
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = {
            viewModel.signUp(
                context,
                userEmail,
                userPassword,
            )
        }) {
            Text(text = "Зарегистрироваться")
        }
        Button(onClick = {
            viewModel.login(
                context,
                userEmail,
                userPassword,
            )
        }) {
            Text(text = "Войти")
        }
//        Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Red), onClick = {
//            viewModel.logout(context)
//        }) {
//            Text(text = "Выйти")
//        }

        when (userState) {
            is UserState.Loading -> {
                LoadingComponent()
            }

            is UserState.Success -> {
                val message = (userState as UserState.Success).message
                navController.navigate(NavigationRoutes.SCREEN)
                currentUserState = message
            }

            is UserState.Error -> {
                val message = (userState as UserState.Error).message
                currentUserState = message
            }
        }

        if (currentUserState.isNotEmpty()) {
            Text(text = currentUserState)
        }
    }
}

@Composable
fun Screen(viewModel: SupabaseAuthViewModel = viewModel(),  navController: NavController) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.isUserLoggedIn(
            context
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(text = "hello")
        Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Red), onClick = {
            viewModel.logout(context)
            navController.navigate(NavigationRoutes.MAIN)
        }) {
            Text(text = "Выйти")
        }
    }
}
