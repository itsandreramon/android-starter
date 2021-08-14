package app.example.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.example.ui.screens.ExampleScreen
import app.example.ui.screens.Screen

@Composable
fun NavContainer(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.One.route) {
        composable(Screen.One.route) {
            ExampleScreen()
        }

        composable(Screen.Two.route) {
            ExampleScreen()
        }
    }
}
