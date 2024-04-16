package it.pierosilvestri.end_to_end_uitesting.util

sealed class UIEvent {

    data class Error(val message: UIText?): UIEvent()
    object Success: UIEvent()
    object NavigateUp: UIEvent()

    data class ShowSnackbar(val message: UIText): UIEvent()
}