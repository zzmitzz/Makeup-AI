package com.iec.makeup.ui.features.authentication.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.iec.makeup.core.ui.AtomicLoadingDialog
import com.iec.makeup.core.utils.validatesEmailPattern
import qrcode.color.Colors


@Composable
fun LoginScreen() {
    val viewModel: LoginScreenVM = hiltViewModel()
    val screenState = viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val effect = viewModel.effect.collectAsStateWithLifecycle(null)

    return Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LoginScreenStateful(
            state = screenState.value,
            effect = effect.value,
            doLogin = { viewModel.doLogin() },
            inputUserName = { viewModel.inputUsername(it) },
            inputPassword = { viewModel.inputPassword(it) }
        )
    }
}

@Composable
fun LoginScreenStateful(
    state: LoginScreenState = LoginScreenState(false, false, null, null),
    effect: LoginScreenEffect? = null,
    doLogin: () -> Unit = {},
    inputUserName: (String) -> Unit = {},
    inputPassword: (String) -> Unit = {},
) {
    var isPasswordVisible by remember { mutableStateOf(true) }
    var isEmailError by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(key1 = effect) {
        if (effect != null) {
            // Navigate action
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFC1CC), // Light Pink
                        Color(0xFFFFE4E1), // Misty Rose
                        Color(0xFFFFF0F5)  // Lavender Blush
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // App Logo/Title
            Text(
                text = "GlowAura",
                style = MaterialTheme.typography.h3.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp
                ),
                color = Color(0xFFFF69B4), // Hot Pink
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Welcome Text
            Text(
                text = "Login to Your Beauty Journey",
                style = MaterialTheme.typography.subtitle1,
                color = Color(0xFFDB7093), // Pale Violet Red
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = state.username ?: "",
                onValueChange = {
                    isEmailError = null
                    inputUserName(it)
                },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xFFFF69B4),
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.Transparent,

                    ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
                        tint = Color(0xFFFF69B4)
                    )
                },

                )
            if (isEmailError != null) {
                Text(
                    text = isEmailError ?: "",
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            // Password Field
            OutlinedTextField(
                value = state.password ?: "",
                onValueChange = {
                    inputPassword(it)
                },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                visualTransformation = if (isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None, // Show or hide password based on the value of isPasswordVisible=,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xFFFF69B4),
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.Transparent,
                ), singleLine = true,
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon",
                        tint = Color(0xFFFF69B4)
                    )
                },
                trailingIcon = {
                    if (isPasswordVisible) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "Hide Password",
                            modifier = Modifier.clickable {
                                isPasswordVisible = !isPasswordVisible
                            }
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "Show Password",
                            modifier = Modifier.clickable {
                                isPasswordVisible = !isPasswordVisible
                            }
                        )
                    }
                }
            )

            // Login Button
            Button(
                onClick = {
                    if (!state.username.isNullOrEmpty() && state.username.validatesEmailPattern()) {
                        doLogin()
                    } else if (state.username.isNullOrEmpty()) {
                        isEmailError = "Email is required"
                    } else {
                        isEmailError = "Invalid email "
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF69B4)
                )
            ) {
                Text(
                    text = "Login",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Forgot Password
            Text(
                text = "Forgot Password?",
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable { /* Handle forgot password */ }
            )

            // Sign Up Text
            Row(
                modifier = Modifier.padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "New to GlowAura? ",
                    color = Color(0xFFDB7093)
                )
                Text(
                    text = "Sign Up",
                    color = Color(0xFFFF69B4),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { /* Handle sign up */ }
                )
            }
        }

        // Decorative Element
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(100.dp)
                .background(
                    Color(0x33FF69B4),
                    shape = CircleShape
                )
        )

        AnimatedVisibility(
            visible = state.isLoading,
            modifier = Modifier.fillMaxSize()
                .clickable {  },
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> -fullHeight },
            ) + fadeIn(),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
            ) + fadeOut(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                AtomicLoadingDialog()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreenStateful()
}