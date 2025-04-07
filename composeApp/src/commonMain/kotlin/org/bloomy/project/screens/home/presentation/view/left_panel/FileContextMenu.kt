@file:OptIn(ExperimentalComposeUiApi::class)

package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.bloomy.project.core.composables.Fullscreen
import org.bloomy.project.core.composables.InPosition
import org.bloomy.project.screens.home.domain.model.FileContextMenuState

@Composable
fun FileContextMenu(
    state: FileContextMenuState?,
    onOutsideClick: () -> Unit,
) {
    if (state != null) {
        println(state.position)
        Fullscreen {
            Box(
                modifier = Modifier
                    .zIndex(20f)
                    .fillMaxSize()
                    .onPointerEvent(
                        PointerEventType.Press,
                    ) {
                        onOutsideClick()
                    }
            ) {
                InPosition(
                    x = state.position.x.toInt(),
                    y = state.position.y.toInt(),
                ) {
                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .height(300.dp)
                            .shadow(
                                10.dp,
                                shape = RoundedCornerShape(10.dp),
                                ambientColor = Color(0x71000000)
                            )
                            .background(Color(0xffffecd3))
                            .zIndex(30f)

                    )
                }
            }
        }
    }
}