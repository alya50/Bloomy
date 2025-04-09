package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.bloomy.project.core.theme.Screen
import org.bloomy.project.core.theme.ScreenSize
import org.bloomy.project.core.theme.ScreenSizeName
import org.bloomy.project.screens.home.domain.model.FilesAction
import org.bloomy.project.screens.home.domain.model.FilesState
import org.bloomy.project.screens.home.domain.model.LeftPanelAction
import org.bloomy.project.screens.home.domain.model.LeftPanelState

val colorStops = arrayOf(
    0.2f to Color(0xffffd198),
    0.4f to Color(0xfffdd9b0),
    1f to Color(0xfffdd9b0),
)

@Composable
fun LeftPanelRoot(
) {

}

@Composable
fun LeftPanel(
    state: LeftPanelState,
    filesState: FilesState,
    onFilesAction: (FilesAction) -> Unit,
    onLeftPanelAction: (LeftPanelAction) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxHeight()
    ) {
        val screenName = ScreenSizeName.collectAsStateWithLifecycle()
        val screenWidth = ScreenSize.collectAsStateWithLifecycle()

        val targetTranslation = if (state.isLeftPanelOpen) {
            0f
        } else {
            when (screenName.value) {
                Screen.SM, Screen.MD -> screenWidth.value.first.toFloat()
                else -> -350f
            }
        }

        val animatedTranslation = animateFloatAsState(
            targetValue = targetTranslation,
            label = "panel-slide"
        )

        val roundedCornerShape: Shape =
            if ((screenName.value == Screen.SM || screenName.value == Screen.MD) && state.isLeftPanelOpen) {
                RoundedCornerShape(0.dp)
            } else RoundedCornerShape(topEnd = 40.dp)


        val panelWidth = animateFloatAsState(
            targetValue =  when (screenName.value) {
                Screen.SM, Screen.MD -> if (state.isLeftPanelOpen) screenWidth.value.first.toFloat() else 0f

                else -> if (state.isLeftPanelOpen) 350f else 0f
            },
            label = "panel-slide"
        )

        Column(
            modifier = Modifier
                .width(panelWidth.value.dp)
                .graphicsLayer {
                    translationX = animatedTranslation.value
                }
                .shadow(
                    10.dp,
                    shape = roundedCornerShape,
                    ambientColor = Color(0x71000000)
                )
                .background(Brush.verticalGradient(colorStops = colorStops))
                .fillMaxHeight()
                .padding(top = 20.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
        ) {
            FileContextMenu(
                state = state.fileContextMenuState,
                onOutsideClick = { onLeftPanelAction(LeftPanelAction.onCloseFileContextMenu) },
                onLeftPanelAction = onLeftPanelAction,
                onFileAction = onFilesAction,
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                SearchButton()
                Spacer(modifier = Modifier.width(20.dp))
                SortButton()
                Spacer(modifier = Modifier.width(20.dp))
                NewFolderButton(
                    onClick = {}
                )
                Spacer(modifier = Modifier.width(20.dp))
                ClosePanelButton(onLeftPanelAction)
            }

            Spacer(modifier = Modifier.height(20.dp))

            FilesView(
                files = filesState.files,
                onFilesAction = onFilesAction,
                onLeftPanelAction = onLeftPanelAction,
                editingFilePath = state.currentlyEditingFilePath,
            )
        }

        if (!state.isLeftPanelOpen) OpenPanelButton(onLeftPanelAction)

    }
}