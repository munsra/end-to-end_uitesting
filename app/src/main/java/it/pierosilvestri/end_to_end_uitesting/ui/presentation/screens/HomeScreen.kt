package it.pierosilvestri.end_to_end_uitesting.ui.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import it.pierosilvestri.end_to_end_uitesting.ui.theme.Endtoend_uitestingTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit
){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home Screen")
        Button(
            modifier = Modifier
                .testTag("logout_button"),
            onClick = { onLogoutClick() }
        ) {
            Text(text = "Logout")
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen(){
    Endtoend_uitestingTheme {
        Scaffold {
            HomeScreen(
                modifier = Modifier.padding(it)
            ) {

            }
        }
    }
}