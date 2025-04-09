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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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
    editingFilePath: String?
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
            onLeftPanelAction = onLeftPanelAction,
            editingFilePath = editingFilePath
        )
    }
}

@Composable
fun IterateFiles(
    files: List<FileAndItsFiles>,
    editingFilePath: String?,
    onFilesAction: (FilesAction) -> Unit,
    onLeftPanelAction: (LeftPanelAction) -> Unit,
) {
    val files = remember (files) { files }
    val editingFilePath = remember (editingFilePath) { editingFilePath }

    Column {
        for (file in files) {
            key(file.path) {
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
                            editingFilePath = editingFilePath
                        )
                    else
                        File(
                            path = file.path,
                            onFileAction = onFilesAction,
                            onLeftPanelAction = onLeftPanelAction,
                            editingFilePath = editingFilePath
                        )
                }
            }
        }
    }
}

@Composable
fun DirectoryView(
    path: String,
    files: List<FileAndItsFiles>?,
    onFileAction: (FilesAction) -> Unit,
    onLeftPanelAction: (LeftPanelAction) -> Unit,
    editingFilePath: String?
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
            currentlyEditingFilePath = editingFilePath
        )

        if (isOpen.value)
            IterateFiles(
                files = files!!,
                onFilesAction = onFileAction,
                onLeftPanelAction = onLeftPanelAction,
                editingFilePath = editingFilePath
            )
    }
}

