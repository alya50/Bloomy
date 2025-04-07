package org.bloomy.project.core.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import java.awt.MouseInfo

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CursorPositionLogger() {
    var layoutCoordinates: LayoutCoordinates? = null

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                layoutCoordinates = coordinates
            }
            .onPointerEvent(PointerEventType.Move) { event ->
                val localOffset = event.changes.first().position
                layoutCoordinates?.let { coordinates ->
                    val windowOffset = coordinates.localToWindow(localOffset)
                    println("Cursor coordinates relative to window: $windowOffset")
                }
            }
    )
}