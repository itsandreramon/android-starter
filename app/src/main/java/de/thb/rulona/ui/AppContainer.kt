package de.thb.rulona.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import de.thb.rulona.nav.NavContainer

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
