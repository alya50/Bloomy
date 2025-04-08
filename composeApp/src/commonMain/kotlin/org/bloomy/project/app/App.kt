package org.bloomy.project.app

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import org.bloomy.project.navigation.main.presentation.MainNavigationHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        RootLayout {
            CompositionLocalProvider(
                LocalRippleConfiguration provides null
            ) {
                MainNavigationHost()
            }
        }
    }
}


@Composable
private fun LogPointerEvents(id: String, content: @Composable () -> Unit = {}) {
    var log by remember { mutableStateOf("") }

    Column {
        Text(log)
        Box(
            Modifier
                .width(100.dp)
                .height(100.dp)
                .background(Color.Red)
                .pointerInput(Unit) {
                    awaitEachGesture {
                        while (true) {
                            val event = awaitPointerEvent()
                            // consume all changes
                            println("Scope")
                            event.changes.forEach { it.consume() }
                        }
                    }
                }
        ) {
            content()
        }
    }
}
