package com.example.cheerup.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cheerup.SupabaseAuthViewModel
import com.example.cheerup.ui.components.AuthButton
import com.example.cheerup.ui.components.EmailTextField
import com.example.cheerup.ui.components.PasswordTextField
import com.example.cheerup.ui.theme.Purple40

@Composable
private fun AuthContent(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isLoginMode: Boolean,
    onToggleAuthMode: () -> Unit,
    onSubmit: () -> Unit,
    isLoading: Boolean,
    errorMessage: String?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Анимированный заголовок
        AnimatedVisibility(
            visible = !isLoading,
            enter = fadeIn() + slideInVertically(initialOffsetY = { -40 }),
            exit = fadeOut()
        ) {
            Text(
                text = if (isLoginMode) "ВХОД" else "РЕГИСТРАЦИЯ",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Purple40,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 48.dp)
            )
        }

        // Поле email
        EmailTextField(
            value = email,
            onValueChange = onEmailChange,
            isError = false,
            errorMessage = null,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле пароля
        PasswordTextField(
            value = password,
            onValueChange = onPasswordChange,
            isError = false,
            errorMessage = null,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Кнопка отправки
        AuthButton(
            onClick = onSubmit,
            isLoading = isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            backgroundColor = Purple40
        ) {
            Text(
                if (isLoginMode) "ВОЙТИ" else "ЗАРЕГИСТРИРОВАТЬСЯ",
                style = MaterialTheme.typography.labelLarge
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка переключения режима
        TextButton(
            onClick = onToggleAuthMode,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (isLoginMode)
                    "НЕТ АККАУНТА? ЗАРЕГИСТРИРОВАТЬСЯ"
                else
                    "УЖЕ ЕСТЬ АККАУНТ? ВОЙТИ",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Purple40,
                    fontWeight = FontWeight.Medium
                )
            )
        }

        // Сообщение об ошибке
        AnimatedVisibility(
            visible = errorMessage != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}