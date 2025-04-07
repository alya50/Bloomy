package org.bloomy.project.core.composables

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text.TextContextMenu
import androidx.compose.runtime.Composable

@OptIn(ExperimentalFoundationApi::class)
object EmptyContextMenu: TextContextMenu {
    @Composable
    override fun Area(textManager: TextContextMenu.TextManager, state: ContextMenuState, content: @Composable () -> Unit) {
        ContextMenuArea({
            emptyList()
        }, state, content = content)
    }
}