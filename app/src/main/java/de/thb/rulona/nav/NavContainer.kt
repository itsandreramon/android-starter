package de.thb.rulona.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.thb.ui.screens.ExampleScreen

@Composable
fun NavContainer(navController: NavHostController) {
    NavHost(navController, startDestination = "examples") {
        composable("examples") {
            ExampleScreen()
        }
    }
}
