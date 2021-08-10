package app.example.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.example.nav.NavContainer
import app.example.ui.components.ExampleBottomNavigation
import app.example.ui.screens.Screen

@Composable
fun AppContainer() {
    val navController = rememberNavController()

    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                AppContent(navController)
            }
        },
    )
}

@Composable
fun AppContent(navController: NavHostController) {
    NavContainer(navController)
}

@Composable
fun ExampleBottomAppBar(navController: NavHostController) {
    val items = listOf(Screen.One, Screen.Two)
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()

    ExampleBottomNavigation(
        items = items,
        currentNavBackStackEntry = currentNavBackStackEntry,
        onClick = { screen ->
            navController.navigate(screen.route) {
                popUpTo(navController.graph.startDestinationRoute!!) {
                    saveState = true
                }

                launchSingleTop = true
                restoreState = true
            }
        },
    )
}
