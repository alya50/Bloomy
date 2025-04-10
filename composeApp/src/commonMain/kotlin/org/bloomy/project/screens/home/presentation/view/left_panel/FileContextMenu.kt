@file:OptIn(ExperimentalComposeUiApi::class)

package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bloomy.composeapp.generated.resources.Res
import bloomy.composeapp.generated.resources.pen_line
import bloomy.composeapp.generated.resources.trash_2
import org.bloomy.project.core.composables.Fullscreen
import org.bloomy.project.core.composables.InPosition
import org.bloomy.project.core.theme.OutfitFontFamily
import org.bloomy.project.core.theme.ScreenSize
import org.bloomy.project.core.theme.ScreenSizeName
import org.bloomy.project.core.theme.size
import org.bloomy.project.screens.home.domain.model.FileContextMenuState
import org.bloomy.project.screens.home.domain.model.FilesAction
import org.bloomy.project.screens.home.domain.model.LeftPanelAction
import org.jetbrains.compose.resources.painterResource

@Composable
fun FileContextMenu(
    state: FileContextMenuState?,
    onOutsideClick: () -> Unit,
    onLeftPanelAction: (LeftPanelAction) -> Unit,
    onFileAction: (FilesAction) -> Unit,
) {
    val screenName = ScreenSizeName.collectAsStateWithLifecycle()
    val screenSize = ScreenSize.collectAsStateWithLifecycle()

    if (state != null) {
        println(state.position)
        Fullscreen {
            val clickCount = remember { mutableStateOf(0) }

            Box(
                modifier = Modifier
                    .zIndex(20f)
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        awaitEachGesture {
                            val up = waitForUpOrCancellation()
                            if (up != null) {
                                up.consume()
                                clickCount.value++
                                if (clickCount.value >= 1) {
                                    onOutsideClick()
                                }
                            }
                        }
                    }
            )
            {
                val isLongPress = state.position == null
                val layoutCoordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }

                println("isLongPress: $isLongPress")
                InPosition(
                    x = if (!isLongPress) state.position!!.x.toInt() else screenSize.value.first.toInt() - (if (layoutCoordinates.value != null) layoutCoordinates.value!!.size.width.toInt() else 0),
                    y = if (!isLongPress) state.position!!.y.toInt() else screenSize.value.second.toInt() -  (if (layoutCoordinates.value != null) layoutCoordinates.value!!.size.height.toInt() else 0),
                ) {

                    val longPressModifier = when (isLongPress) {
                        true -> Modifier
                            .fillMaxWidth()

                        false -> Modifier
                    }

                    Box(
                        modifier = Modifier
                            .onGloballyPositioned {
                                layoutCoordinates.value = it
                            }
                            .padding(if (isLongPress) 0.dp else 10.dp)
                    ) {
                        Box(
                            modifier = longPressModifier
                                .shadow(
                                    10.dp,
                                    shape = RoundedCornerShape(10.dp),
                                    ambientColor = Color(0x71000000)
                                )
                                .background(Color(0xffffecd3))
                                .zIndex(30f)
                                .pointerInput(Unit) {
                                    detectTapGestures { offset ->
                                        // Consume the tap event, preventing it from propagating
                                        // to the parent.  Alternatively, you could use
                                        // pointerInput {  awaitPointerEventScope { ... } } and
                                        // check/consume within the scope.
                                    }
                                }
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(
                                    space = 1.dp,
                                    alignment = Alignment.CenterVertically
                                ),
                                modifier = Modifier.widthIn(max = 200.dp)
                            ) {
                                MenuItem(
                                    text = "Rename",
                                    onClick = {
                                        println("Rename function")
                                        onLeftPanelAction(
                                            LeftPanelAction.RenameFile(
                                                state.clickedFilePath,
                                            )
                                        )
                                        onOutsideClick()
                                    },
                                    icon = painterResource(
                                        resource = Res.drawable.pen_line,
                                    )
                                )

                                MenuItem(
                                    text = "Delete",
                                    onClick = {
                                        onFileAction(FilesAction.DeleteFile(state.clickedFilePath))
                                        onOutsideClick()
                                    },
                                    icon = painterResource(
                                        resource = Res.drawable.trash_2,
                                    )
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
private fun ColumnScope.MenuItem(
    text: String,
    onClick: () -> Unit,
    icon: Painter
) {
    val hovering = remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(space = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(if (hovering.value) Color(0x88fcd5a4) else Color.Transparent)
            .padding(10.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    onClick()
                }
            }
            .pointerInput(PointerEventType.Enter) { hovering.value = true }
            .pointerInput(PointerEventType.Exit) { hovering.value = false }

    ) {
        Image(
            painter = icon,
            contentDescription = text,
            modifier = Modifier
                .size(24.dp)
        )

        Text(
            text = text,
            fontFamily = OutfitFontFamily(),
            color = Color(0xff000000),
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
        )
    }
}