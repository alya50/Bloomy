package org.bloomy.project

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import org.bloomy.project.screens.home.domain.model.Folder
import org.bloomy.project.screens.home.domain.model.LeftPanelState
import org.bloomy.project.screens.home.presentation.view.HomeScreen

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        leftPanelState = LeftPanelState(
            folders = listOf(
                Folder(
                    name = "Folder 1",
                    files = listOf(
                        "file1",
                        "file2",
                        "file3",
                    )
                ),
            ),
        )
    )
}


