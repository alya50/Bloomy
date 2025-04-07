package org.bloomy.project.app

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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

