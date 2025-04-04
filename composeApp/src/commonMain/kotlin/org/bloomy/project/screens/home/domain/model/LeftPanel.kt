package org.bloomy.project.screens.home.domain.model

import java.io.File

data class LeftPanelState(
    val folders: List<Folder> = listOf(),
    val searchResults: List<SearchResult> = listOf(),
    val selectedFolder: Folder? = null,
    val selectedFile: File? = null,
    val isSearching: Boolean = false,
    val searchQuery: String = "",
    
)

data class SearchResult(
    val fileName: String,
    val title: String,
    val description: String,
)

data class Folder(
    val name: String,
    val files: List<String> = listOf(),
)

sealed class LeftPanelAction {
    data class onFolderClick(val folder: Folder): LeftPanelAction()
    data class onSearchQueryChanged(val query: String): LeftPanelAction()
}