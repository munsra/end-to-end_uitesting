package it.pierosilvestri.end_to_end_uitesting.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import it.pierosilvestri.end_to_end_uitesting.ui.presentation.screens.HomeScreen
import it.pierosilvestri.end_to_end_uitesting.ui.presentation.screens.login.LoginScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Login.route) {
            LoginScreen(
                onLoginClick = {
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