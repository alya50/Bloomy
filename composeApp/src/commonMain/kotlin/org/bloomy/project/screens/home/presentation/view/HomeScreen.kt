package org.bloomy.project.screens.home.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.bloomy.project.screens.home.domain.model.FilesAction
import org.bloomy.project.screens.home.domain.model.FilesState
import org.bloomy.project.screens.home.domain.model.LeftPanelAction
import org.bloomy.project.screens.home.domain.model.LeftPanelState
import org.bloomy.project.screens.home.presentation.view.chat.Chat
import org.bloomy.project.screens.home.presentation.view.editor.Editor
import org.bloomy.project.screens.home.presentation.view.left_panel.LeftPanel
import org.bloomy.project.screens.home.presentation.viewModels.FilesViewModel
import org.bloomy.project.screens.home.presentation.viewModels.LeftPanelViewModel

@Composable
fun HomeScreenRoot(
    leftPanelViewModel: LeftPanelViewModel,
    filesViewModel: FilesViewModel,
) {
    val leftPanelState = leftPanelViewModel.state.collectAsStateWithLifecycle()
    val filesState = filesViewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        leftPanelState = leftPanelState.value,
        onLeftPanelAction = leftPanelViewModel::onAction,
        filesState = filesState.value,
        onFilesAction = filesViewModel::onAction,
    )
}

@Composable
fun HomeScreen(
    leftPanelState: LeftPanelState,
    filesState: FilesState,
    onLeftPanelAction: (LeftPanelAction) -> Unit,
    onFilesAction: (FilesAction) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffde3c3))
    ) {
        LeftPanel(
            state = leftPanelState,
            onFilesAction = onFilesAction,
            filesState = filesState,
            onLeftPanelAction = onLeftPanelAction,
        )
        Editor()
        Chat(false,{},{})
    }
}