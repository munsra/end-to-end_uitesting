package it.pierosilvestri.end_to_end_uitesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import it.pierosilvestri.end_to_end_uitesting.ui.presentation.navigation.AppNavHost
import it.pierosilvestri.end_to_end_uitesting.ui.theme.Endtoend_uitestingTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Endtoend_uitestingTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
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
}