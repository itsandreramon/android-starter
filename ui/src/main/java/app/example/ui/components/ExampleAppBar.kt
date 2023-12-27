package app.example.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.example.ui.theme.padding_medium

@Composable
fun ExampleAppBar(title: String) {
    Surface(elevation = 0.dp, color = MaterialTheme.colors.surface) {
        Column(
            modifier = Modifier
                .padding(start = padding_medium, end = padding_medium)
                .statusBarsPadding()
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Text(text = title, style = MaterialTheme.typography.h4, fontWeight = FontWeight.Black)
        }
    }
}
