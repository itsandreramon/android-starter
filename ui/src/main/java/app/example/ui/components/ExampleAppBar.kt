package app.example.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun ExampleAppBar(
    title: String,
    actions: List<AppBarAction> = listOf()
) {
    Surface(elevation = 4.dp, color = MaterialTheme.colors.surface) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier.statusBarsPadding()
        ) {
            TopAppBar(
                title = { Text(title) },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                actions = {
                    for (action in actions) {
                        IconButton(onClick = action.onClick) {
                            Icon(action.icon, action.contentDescription)
                        }
                    }
                }
            )
        }
    }
}

sealed class AppBarAction(
    val icon: ImageVector,
    val contentDescription: String?,
    val onClick: () -> Unit,
) {
    class Back(onClick: () -> Unit) : AppBarAction(
        icon = Icons.Filled.ArrowBack,
        contentDescription = "Back",
        onClick = onClick,
    )
}