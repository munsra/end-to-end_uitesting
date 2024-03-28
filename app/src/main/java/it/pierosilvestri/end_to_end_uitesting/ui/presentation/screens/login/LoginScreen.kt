package it.pierosilvestri.end_to_end_uitesting.ui.presentation.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import it.pierosilvestri.end_to_end_uitesting.ui.theme.Endtoend_uitestingTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginClick: () -> Unit,
) {
    val email = viewModel.email
    val password = viewModel.password

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            modifier = Modifier.testTag("email_textfield"),
            value = email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Email") }
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
                if (viewModel.areFieldsValid()) {
                    onLoginClick()
                } else {
                    viewModel.showError()
                }
            },
        ) {
            Text("Login")
        }
        if (viewModel.error)
            Text(
                modifier = Modifier.testTag("error_msg"),
                text = "Error! Fill all fields"
            )
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    Endtoend_uitestingTheme {
        Scaffold {
            LoginScreen(
                modifier = Modifier.padding(it)
            ) {

            }
        }
    }
}