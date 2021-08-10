package app.example.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import app.example.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDecorFitsSystemWindows(window, false)

        setContent {
            AppTheme {
                AppContainer()
            }
        }
    }
}
