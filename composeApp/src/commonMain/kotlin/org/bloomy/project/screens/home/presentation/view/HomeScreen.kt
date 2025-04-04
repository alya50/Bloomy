package org.bloomy.project.screens.home.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.bloomy.project.screens.home.domain.model.Folder
import org.bloomy.project.screens.home.domain.model.LeftPanelState
import org.bloomy.project.screens.home.presentation.view.chat.Chat
import org.bloomy.project.screens.home.presentation.view.editor.Editor
import org.bloomy.project.screens.home.presentation.view.left_panel.LeftPanel

@Composable
fun HomeScreenRoot(
) {
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
                Folder(
                    name = "Folder 2",
                    files = listOf(
                        "file1",
                        "file2",
                        "file3",
                    )
                ),
                Folder(
                    name = "Folder 3",
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

@Composable
fun HomeScreen(
    leftPanelState: LeftPanelState,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffde3c3))
    ) {
        LeftPanel(
            state = leftPanelState,
        )
        Editor()
        Chat()
    }
}