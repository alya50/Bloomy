@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class
)

package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.LocalTextContextMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.bloomy.project.core.composables.EmptyContextMenu
import org.bloomy.project.screens.home.domain.model.FileAndItsFiles
import org.bloomy.project.screens.home.domain.model.FilesAction
import org.bloomy.project.screens.home.domain.model.LeftPanelAction

@Composable
fun FilesView(
    files: MutableList<FileAndItsFiles>,
    onFilesAction: (FilesAction) -> Unit,
    onLeftPanelAction: (LeftPanelAction) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(lifecycleState) {
        if (lifecycleState == Lifecycle.State.RESUMED) {
            onFilesAction(FilesAction.ReloadFiles)
        }
    }

    CompositionLocalProvider(
        LocalTextContextMenu provides EmptyContextMenu
    ) {
        IterateFiles(
            files = files,
            onFilesAction = onFilesAction,
            onLeftPanelAction = onLeftPanelAction
        )
    }
}

@Composable
fun IterateFiles(
    files: List<FileAndItsFiles>,
    onFilesAction: (FilesAction) -> Unit,
    onLeftPanelAction: (LeftPanelAction) -> Unit,
) {
    for (file in files) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)

        ) {
            if (file.files?.isNotEmpty() == true)
                DirectoryView(
                    path = file.path,
                    files = file.files,
                    onFileAction = onFilesAction,
                    onLeftPanelAction = onLeftPanelAction,
                )
            else
                File(
                    path = file.path,
                    onFileAction = onFilesAction,
                    onLeftPanelAction = onLeftPanelAction,
                )
        }
    }
}

@Composable
fun DirectoryView(
    path: String,
    files: List<FileAndItsFiles>?,
    onFileAction: (FilesAction) -> Unit,
    onLeftPanelAction: (LeftPanelAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)

    ) {
        val isOpen = remember { mutableStateOf(false) }

        Directory(
            path = path,
            onClick = { isOpen.value = !isOpen.value },
            isOpen = isOpen.value,
            onFileAction = onFileAction,
            onLeftPanelAction = onLeftPanelAction,
        )

        if (isOpen.value)
            IterateFiles(
                files = files!!,
                onFilesAction = onFileAction,
                onLeftPanelAction = onLeftPanelAction
            )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun Modifier.onFilePress(
    onClick: () -> Unit,
    layoutCoordinates: MutableState<LayoutCoordinates?>,
    onLeftPanelAction: (LeftPanelAction) -> Unit,
    path: String,
) = Modifier.onPointerEvent(
    PointerEventType.Press,
) { event ->
    println("Pressed")
    if (event.buttons.isSecondaryPressed) {
        println("Secondary pressed")
            val localOffset = event.changes.first().position
if (layoutCoordinates.value == null) println("Layout coordinates is null")
        layoutCoordinates.value?.let { coordinates ->
            val windowOffset = coordinates.localToWindow(localOffset)
println("Window offset: $windowOffset")
            onLeftPanelAction(
                LeftPanelAction.OnFileRightClick(
                    path,
                    windowOffset
                )
            )
        }
    } else onClick()
}
