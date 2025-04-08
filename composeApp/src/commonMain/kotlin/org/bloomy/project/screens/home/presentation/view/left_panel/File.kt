@file:OptIn(ExperimentalComposeUiApi::class)

package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import org.bloomy.project.core.composables.EditableText
import org.bloomy.project.core.theme.OutfitFontFamily
import org.bloomy.project.screens.home.domain.model.FilesAction
import org.bloomy.project.screens.home.domain.model.LeftPanelAction

@Composable
fun File(
    path: String,
    onFileAction: (FilesAction) -> Unit,
    onLeftPanelAction: (LeftPanelAction) -> Unit,
) {
    val name = path.substringAfterLast("/")
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val backgroundColor = if (isHovered) Color(0x1f000000) else Color.Transparent
    val layoutCoordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                layoutCoordinates.value = coordinates
            }
            .onPointerEvent(
                PointerEventType.Press,
            ) { event ->
                if (event.buttons.isSecondaryPressed) {
                    val localOffset = event.changes.first().position

                    layoutCoordinates.value?.let { coordinates ->
                        val windowOffset = coordinates.localToWindow(localOffset)
                        onLeftPanelAction(
                            LeftPanelAction.OnFileRightClick(
                                path,
                                windowOffset
                            )
                        )
                    }
                } else {
                    onLeftPanelAction(LeftPanelAction.OnFileClick(path))
                    onFileAction(FilesAction.SelectTab(path))
                }
            }
    ) {
        val fileName = rememberSaveable { MutableStateFlow(name) }
        val editing = rememberSaveable { mutableStateOf(false) }

        EditableText(
            value = fileName.value,
            onValueChange = {
                fileName.value = it
            },
            onClick = {},
            fontFamily = OutfitFontFamily(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            keyboardActions = KeyboardActions(
                onDone = {
                    onFileAction(FilesAction.RenameFile(path, fileName.value))
                },
            ),
            editing = editing.value,
            setEditing = { editing.value = !editing.value },
        )
    }
}

