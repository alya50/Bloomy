package org.bloomy.project

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import org.bloomy.project.screens.home.domain.model.FilesState
import org.bloomy.project.screens.home.domain.model.LeftPanelState
import org.bloomy.project.screens.home.presentation.view.HomeScreen

@Preview
@Composable
fun PreviewBloomy() {
    HomeScreen(
        onFilesAction = {},
        leftPanelState = LeftPanelState(),
        onLeftPanelAction = {},
        filesState = FilesState(
        )
    )
}