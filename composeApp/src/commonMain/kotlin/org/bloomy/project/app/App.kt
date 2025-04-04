package org.bloomy.project.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.bloomy.project.navigation.main.presentation.MainNavigationHost

@Composable
@Preview
fun App() {
    MaterialTheme {
        RootLayout {
            MainNavigationHost()
        }
    }
}