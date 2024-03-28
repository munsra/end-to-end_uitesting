package it.pierosilvestri.end_to_end_uitesting

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.common.truth.Truth.assertThat
import it.pierosilvestri.end_to_end_uitesting.ui.presentation.navigation.AppNavHost
import it.pierosilvestri.end_to_end_uitesting.ui.presentation.navigation.NavigationItem
import it.pierosilvestri.end_to_end_uitesting.ui.theme.Endtoend_uitestingTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginNavigationE2E {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: NavHostController

    @Before
    fun setUp(){
        composeRule.activity.setContent {
            Endtoend_uitestingTheme {
                navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AppNavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                    )
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