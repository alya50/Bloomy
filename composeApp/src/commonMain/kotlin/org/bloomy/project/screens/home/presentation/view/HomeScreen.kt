package org.bloomy.project.screens.home.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.bloomy.project.screens.home.domain.model.LeftPanelAction
import org.bloomy.project.screens.home.domain.model.LeftPanelState
import org.bloomy.project.screens.home.presentation.view.chat.Chat
import org.bloomy.project.screens.home.presentation.view.editor.Editor
import org.bloomy.project.screens.home.presentation.view.left_panel.LeftPanel
import org.bloomy.project.screens.home.presentation.viewModels.LeftPanelViewModel

@Composable
fun HomeScreenRoot(
    leftPanelViewModel: LeftPanelViewModel
) {
    val leftPanelState = leftPanelViewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        leftPanelState = leftPanelState.value,
        onLeftPanelAction = leftPanelViewModel::onAction
    )
}

@Composable
fun HomeScreen(
    leftPanelState: LeftPanelState,
    onLeftPanelAction: (LeftPanelAction) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffde3c3))
    ) {
        LeftPanel(
            state = leftPanelState,
            onAction = onLeftPanelAction,
        )
        Editor()
        Chat()
    }
}