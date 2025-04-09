@file:OptIn(ExperimentalComposeUiApi::class)

package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bloomy.composeapp.generated.resources.Res
import bloomy.composeapp.generated.resources.right
import kotlinx.coroutines.flow.MutableStateFlow
import org.bloomy.project.core.composables.EditableText
import org.bloomy.project.core.theme.OutfitFontFamily
import org.bloomy.project.screens.home.domain.model.FilesAction
import org.bloomy.project.screens.home.domain.model.LeftPanelAction
import org.jetbrains.compose.resources.painterResource


@Composable
fun Directory(
    path: String,
    onLeftPanelAction: (LeftPanelAction) -> Unit,
    onFileAction: (FilesAction) -> Unit,
    isOpen: Boolean,
    onClick: () -> Unit,
    currentlyEditingFilePath: String?
) {
    val name = remember(path) { path.substringAfterLast("/") }
    val animatedIconRotation: Float by animateFloatAsState(
        if (isOpen) 90f else 0f,
        label = "folder_icon_rotation"
    )
    val editing by derivedStateOf { mutableStateOf(currentlyEditingFilePath == path) }
    val layoutCoordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val backgroundColor = if (isHovered) Color(0x1f000000) else Color.Transparent

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .padding(5.dp)
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
                } else onClick()
            }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(Res.drawable.right),
                contentDescription = null,
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp)
                    .rotate(animatedIconRotation)
            )

            val fileName = remember(name) { MutableStateFlow(name) }

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
                setIsDone = {
                    onFileAction(FilesAction.RenameFile(path, fileName.value))
                    onLeftPanelAction(LeftPanelAction.RenamingFileIsDone)
                },
            )
        }

    }
}
