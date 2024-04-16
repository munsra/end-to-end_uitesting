package it.pierosilvestri.end_to_end_uitesting

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import it.pierosilvestri.end_to_end_uitesting.ui.presentation.navigation.NavigationItem
import it.pierosilvestri.end_to_end_uitesting.ui.presentation.screens.home.HomeScreen
import it.pierosilvestri.end_to_end_uitesting.ui.presentation.screens.login.LoginScreen
import it.pierosilvestri.end_to_end_uitesting.ui.presentation.screens.login.LoginViewModel
import it.pierosilvestri.end_to_end_uitesting.ui.theme.Endtoend_uitestingTheme
import it.pierosilvestri.end_to_end_uitesting.use_case.ValidateLogin
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginNavigationE2E {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var validateLogin: ValidateLogin
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var navController: NavHostController
    private lateinit var snackbarHostState: SnackbarHostState

    @Before
    fun setUp(){
        hiltRule.inject()
        validateLogin = ValidateLogin()
        loginViewModel = LoginViewModel(
            validateLogin = validateLogin
        )
        composeRule.activity.setContent {
            Endtoend_uitestingTheme {
                navController = rememberNavController()
                snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    NavHost(
                        modifier =  Modifier.padding(it),
                        navController = navController,
                        startDestination = NavigationItem.Login.route
                    ) {
                        composable(NavigationItem.Login.route) {
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate(NavigationItem.Home.route)
                                },
                                snackbarHostState = snackbarHostState,
                                viewModel = loginViewModel
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
            }
        }
    }

    @Test
    fun loginError(){

        composeRule
            .onNodeWithTag("error_msg")
            .assertDoesNotExist()

        composeRule
            .onNodeWithText("Login")
            .performClick()

        composeRule
            .onNodeWithTag("error_msg")
            .assertIsDisplayed()

        composeRule
            .onNodeWithTag("email_textfield")
            .performTextInput("prova@email.com")

        composeRule
            .onNodeWithTag("error_msg")
            .assertDoesNotExist()
    }

    @Test
    fun loginToHome_homeToLoginFlow() {

        composeRule
            .onNodeWithTag("email_textfield")
            .performTextInput("prova@email.com")

        composeRule
            .onNodeWithTag("password_textfield")
            .performTextInput("123456789")

        composeRule
            .onNodeWithText("Login")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(NavigationItem.Home.route)
        ).isTrue()

        composeRule
            .onNodeWithTag("logout_button")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(NavigationItem.Login.route)
        ).isTrue()
    }
}