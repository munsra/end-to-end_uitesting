package it.pierosilvestri.end_to_end_uitesting.ui.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var error by mutableStateOf(false)
        private set

    fun onEmailChange(newEmail: String) {
        email = newEmail
        hideError()
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
        hideError()
    }

    fun areFieldsValid(): Boolean = email.isNotEmpty() && password.isNotEmpty()

    fun showError(){
        error = true
    }

    fun hideError(){
        error = false
    }
}
