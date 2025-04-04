package org.bloomy.project.screens.home.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.bloomy.project.screens.home.presentation.view.chat.Chat
import org.bloomy.project.screens.home.presentation.view.editor.Editor
import org.bloomy.project.screens.home.presentation.view.left_panel.LeftPanel

@Composable
fun HomeScreenRoot(
) {
    HomeScreen()
}

@Composable
fun HomeScreen(

) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffde3c3))
    ) {
        LeftPanel()
        Editor()
        Chat()
    }
}