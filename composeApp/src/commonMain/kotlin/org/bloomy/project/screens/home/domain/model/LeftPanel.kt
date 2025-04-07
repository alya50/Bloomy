package org.bloomy.project.screens.home.domain.model

import androidx.compose.ui.geometry.Offset

data class LeftPanelState(
    val searchResults: MutableList<SearchResult> = mutableListOf(),
    // the file which was last clicked
    val lastClickedFile: String? = null,
    // whether the search is currently running
    val isSearching: Boolean = false,
    // currently searched query
    val searchQuery: String? = null,
    // menu which appers when right clicking on a file
    val fileContextMenuState: FileContextMenuState? = null,
    // currently editing filename path
    val editingFilename: String? = null,
)

data class SearchResult(
    val fileName: String,
    val title: String,
    val description: String,
)

sealed class LeftPanelAction {
    /*
    * Will open context menu on right click
    * */
    data class OnFileRightClick(
        val filePath: String,
        val position: Offset,
    ) : LeftPanelAction()

    /*
    * Allows us to see last interacted file.
    * This allows us to make some changes like
    *  determining where new folder button creates new folder.
    * */
    data class OnFileClick(
        val filePath: String,
    ) : LeftPanelAction()

    data object onCloseFileContextMenu : LeftPanelAction()
}

data class FileContextMenuState(
    val position: Offset,
)