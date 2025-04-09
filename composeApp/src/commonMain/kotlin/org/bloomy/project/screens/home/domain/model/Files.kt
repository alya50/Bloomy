package org.bloomy.project.screens.home.domain.model

import java.io.File

data class FilesState(
    val files: MutableList<FileAndItsFiles> = mutableListOf(),
    val currentTabs: HashMap<String, File> = hashMapOf(),
    val selectedTabIndex: Int = 0,
)

// We are using same data class for both folders and files because folders are also files
data class FileAndItsFiles(
    val path: String,
    val files: MutableList<FileAndItsFiles>? = null,
)

sealed class FilesAction {
    data object ReloadFiles : FilesAction()
    data class RenameFile(val filePath: String, val newName: String) : FilesAction()
    data class SelectTab(val filePath: String) : FilesAction()
    data class CloseTab(val filePath: String) : FilesAction()
    data class DeleteFile(val filePath: String) : FilesAction()
}
