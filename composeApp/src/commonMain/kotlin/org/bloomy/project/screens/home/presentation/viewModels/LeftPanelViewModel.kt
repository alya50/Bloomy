package org.bloomy.project.screens.home.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.bloomy.project.screens.home.domain.model.FileContextMenuState
import org.bloomy.project.screens.home.domain.model.LeftPanelAction
import org.bloomy.project.screens.home.domain.model.LeftPanelState


class LeftPanelViewModel : ViewModel() {
    private val _state = MutableStateFlow(LeftPanelState())
    val state = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: LeftPanelAction) {
        when (action) {
            is LeftPanelAction.OnFileRightClick -> {
                _state.value = _state.value.copy(
                    fileContextMenuState = FileContextMenuState(
                        position = action.position,
                        clickedFilePath = action.filePath
                    ),
                )
            }

            is LeftPanelAction.OnFileClick -> {
                _state.value = _state.value.copy(
                    lastClickedFile = action.filePath,
                )
            }

            LeftPanelAction.onCloseFileContextMenu -> {
                _state.value = _state.value.copy(
                    fileContextMenuState = null,
                )
            }

            is LeftPanelAction.RenameFile -> {
                _state.value = _state.value.copy(
                    currentlyEditingFilePath = action.filePath,
                )
            }

            LeftPanelAction.RenamingFileIsDone -> {
                _state.value = _state.value.copy(
                    currentlyEditingFilePath = null,
                )
            }

            LeftPanelAction.closeLeftPanel -> {
                _state.value = _state.value.copy(
                    isLeftPanelOpen = false,
                )
            }
            LeftPanelAction.openLeftPanel -> {
                _state.value = _state.value.copy(
                    isLeftPanelOpen = true,
                )
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _state.value = _state.value.copy(
            searchQuery = query
        )

        _state.value = _state.value.copy(
            searchQuery = query,
            isSearching = true
        )
    }
}