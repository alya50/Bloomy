package org.bloomy.project.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.bloomy.project.navigation.main.presentation.DrawerLogicRoot
import org.bloomy.project.navigation.main.presentation.DrawerLogicViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        RootLayout {
            val drawerLogicViewModel = koinViewModel<DrawerLogicViewModel>()

            DrawerLogicRoot(
                viewModel = drawerLogicViewModel
            )
        }
    }
}