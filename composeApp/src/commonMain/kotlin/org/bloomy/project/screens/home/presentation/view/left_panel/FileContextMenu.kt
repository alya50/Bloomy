package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import bloomy.composeapp.generated.resources.Res
import bloomy.composeapp.generated.resources.pen_line
import bloomy.composeapp.generated.resources.trash_2
import org.bloomy.project.core.composables.Fullscreen
import org.bloomy.project.core.composables.InPosition
import org.bloomy.project.core.theme.OutfitFontFamily
import org.bloomy.project.core.theme.size
import org.bloomy.project.screens.home.domain.model.FileContextMenuState
import org.jetbrains.compose.resources.painterResource

@Composable
fun FileContextMenu(
    state: FileContextMenuState?,
    onOutsideClick: () -> Unit,
) {
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
                InPosition(
                    x = state.position.x.toInt(),
                    y = state.position.y.toInt(),
                ) {
                    Box(
                        modifier = Modifier
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
                                space = 15.dp,
                                alignment = Alignment.CenterVertically
                            ),
                            modifier = Modifier
                                .padding(
                                    top = 10.dp,
                                    bottom = 10.dp,
                                    start = 10.dp,
                                    end = 50.dp
                                )
                        ) {
                            MenuItem(
                                text = "Rename",
                                onClick = { },
                                icon = painterResource(
                                    resource = Res.drawable.pen_line,
                                )
                            )

                            MenuItem(
                                text = "Delete",
                                onClick = { },
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

@Composable
private fun MenuItem(
    text: String,
    onClick: () -> Unit,
    icon: Painter
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = 20.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures {
                    onClick()
                }
            }
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