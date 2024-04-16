package it.pierosilvestri.end_to_end_uitesting.ui.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import it.pierosilvestri.end_to_end_uitesting.ui.presentation.screens.home.HomeScreen
import it.pierosilvestri.end_to_end_uitesting.ui.presentation.screens.login.LoginScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route,
    snackbarHostState: SnackbarHostState,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Login.route) {
            LoginScreen(
                snackbarHostState = snackbarHostState,
                onLoginSuccess = {
                    navController.navigate(NavigationItem.Home.route)
                }
            )
        }
        composable(NavigationItem.Home.route) {
            HomeScreen(
                onLogoutClick = {
                    navController.navigate(NavigationItem.Login.route)
                }
            )
        }
    }
}