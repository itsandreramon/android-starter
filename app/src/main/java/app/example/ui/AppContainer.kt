package app.example.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.example.nav.NavContainer

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
