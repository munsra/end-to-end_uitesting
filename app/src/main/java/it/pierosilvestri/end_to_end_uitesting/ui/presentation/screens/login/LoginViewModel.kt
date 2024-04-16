package it.pierosilvestri.end_to_end_uitesting.ui.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.pierosilvestri.end_to_end_uitesting.use_case.ValidateLogin
import it.pierosilvestri.end_to_end_uitesting.util.UIEvent
import it.pierosilvestri.end_to_end_uitesting.util.UIText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLogin: ValidateLogin
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var error by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")

    fun onEmailChange(newEmail: String) {
        email = newEmail
        hideError()
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
        hideError()
    }

    fun onLoginClick(){
        val result = validateLogin(
            email = email,
            password = password
        )
        when(result) {
            is ValidateLogin.Result.Success -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.NavigateUp)
                }
            }
            is ValidateLogin.Result.Error -> {
                viewModelScope.launch {
                    _uiEvent.send(UIEvent.ShowSnackbar(result.message))
                    _uiEvent.send(
                        UIEvent.Error(result.message)
                    )
                }
            }
        }
    }

    fun updateErrorMessage(value: String){
        errorMessage = value
    }

    fun areFieldsValid(): Boolean = email.isNotEmpty() && password.isNotEmpty()

    fun showError(){
        error = true
    }

    fun hideError(){
        error = false
    }
}
