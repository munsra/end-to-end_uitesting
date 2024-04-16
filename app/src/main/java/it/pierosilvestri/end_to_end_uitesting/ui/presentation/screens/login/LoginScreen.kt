package it.pierosilvestri.end_to_end_uitesting.ui.presentation.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import it.pierosilvestri.end_to_end_uitesting.ui.theme.Endtoend_uitestingTheme
import it.pierosilvestri.end_to_end_uitesting.util.UIEvent
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    onLoginSuccess: () -> Unit,
) {
    val email = viewModel.email
    val password = viewModel.password
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = keyboardController) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.ShowSnackbar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = event.message.asString(context)
                        )
                        keyboardController?.hide()
                    }
                }

                is UIEvent.NavigateUp -> onLoginSuccess()
                is UIEvent.Error -> {
                    viewModel.updateErrorMessage(event.message?.asString(context).orEmpty())
                    viewModel.showError()
                }
                else -> Unit
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            modifier = Modifier.testTag("email_textfield"),
            value = email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        OutlinedTextField(
            modifier = Modifier.testTag("password_textfield"),
            value = password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            onClick = {
                viewModel.onLoginClick()
            },
        ) {
            Text("Login")
        }
        if (viewModel.error)
            Text(
                modifier = Modifier.testTag("error_msg"),
                text = viewModel.errorMessage
            )
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    Endtoend_uitestingTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
        ) {

            LoginScreen(
                modifier = Modifier.padding(it),
                snackbarHostState = snackbarHostState,
            ) {

            }
        }
    }
}