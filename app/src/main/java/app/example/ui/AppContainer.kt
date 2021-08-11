package app.example.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.example.nav.NavContainer
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun AppContainer() {
    val navController = rememberNavController()

    Scaffold {
        Box(Modifier.navigationBarsPadding()) {
            AppContent(navController)
        }
    }
}

@Composable
fun AppContent(navController: NavHostController) {
    NavContainer(navController)
}
