package org.bloomy.project.screens.home.domain.model

import java.io.File

data class LeftPanelState(
    val files: MutableList<FileData> = mutableListOf(),
    val searchResults: MutableList<SearchResult> = mutableListOf(),
    val selectedFile: File? = null,
    val isSearching: Boolean = false,
    val searchQuery: String = ""
)

data class SearchResult(
    val fileName: String,
    val title: String,
    val description: String,
)

// We are using same data class for both folders and files because folders are also files
data class FileData(
    val current: File,
    val directoryContents: MutableList<FileData>? = null,
)

sealed class LeftPanelAction {
    data class onFolderRename(val file: File, val newName: String) : LeftPanelAction()
    data class onSearchQueryChanged(val query: String) : LeftPanelAction()
    data object onDirectoryRefresh : LeftPanelAction()
}