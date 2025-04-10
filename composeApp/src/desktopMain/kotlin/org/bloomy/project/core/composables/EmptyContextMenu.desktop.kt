@file:OptIn(ExperimentalFoundationApi::class)

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text.LocalTextContextMenu
import androidx.compose.foundation.text.TextContextMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
actual fun EmptyContextMenu(content: @Composable () -> Unit): Unit {
    return CompositionLocalProvider(
        LocalTextContextMenu provides EmptyContextMenuObj
    ) {
       content()
    }
}

@OptIn(ExperimentalFoundationApi::class)
object EmptyContextMenuObj: TextContextMenu {
    @Composable
    override fun Area(textManager: TextContextMenu.TextManager, state: ContextMenuState, content:@Composable () -> Unit) {
        ContextMenuArea({
            emptyList()
        }, state, content = content)
    }
}