package it.pierosilvestri.end_to_end_uitesting.ui.presentation.navigation

enum class Screen {
    LOGIN,
    HOME
}

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Login : NavigationItem(Screen.LOGIN.name)
}