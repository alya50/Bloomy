package org.bloomy.project.core.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup

/**
 * Fullscreen allows to create a box that overlays the whole screen.
 */
@Composable
fun Fullscreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Popup (
        onDismissRequest = {},
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            content()
        }
    }
}