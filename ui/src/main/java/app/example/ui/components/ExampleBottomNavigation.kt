package app.example.ui.components

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import app.example.ui.screens.Screen

@Composable
fun ExampleBottomNavigation(
    items: List<Screen>,
    currentNavBackStackEntry: NavBackStackEntry?,
    onClick: (Screen) -> Unit,
) {
    Surface(
        elevation = 4.dp,
        color = MaterialTheme.colors.primary,
    ) {
        BottomNavigation(
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            modifier = Modifier.navigationBarsPadding(),
        ) {
            val currentRoute = currentNavBackStackEntry?.destination?.route

            items.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        screen.icon?.let { icon ->
                            Icon(icon, contentDescription = null)
                        }
                    },
                    label = { Text(text = stringResource(screen.titleResId)) },
                    selected = currentRoute == screen.route,
                    onClick = {
                        if (currentRoute != screen.route) {
                            onClick(screen)
                        }
                    },
                )
            }
        }
    }
}
