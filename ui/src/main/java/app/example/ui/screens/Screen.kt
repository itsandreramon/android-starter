package app.example.ui.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.ui.graphics.vector.ImageVector
import app.example.ui.R

sealed class Screen(val route: String, @StringRes val titleResId: Int, val icon: ImageVector?) {
    object One : Screen("one", R.string.screen_title_one, Icons.Filled.People)
    object Two : Screen("two", R.string.screen_title_two, Icons.Filled.People)
}
