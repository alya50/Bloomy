package org.bloomy.project.screens.home.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.bloomy.project.screens.home.domain.model.LeftPanelAction
import org.bloomy.project.screens.home.domain.model.LeftPanelState


class LeftPanelViewModel (): ViewModel() {
    private val _state = mutableStateOf(LeftPanelState())
    val state = _state

    fun onAction(action: LeftPanelAction) {
        when (action) {
            is LeftPanelAction.onFolderClick -> {
                // TODO
            }
            is LeftPanelAction.onSearchQueryChanged -> {
                // TODO
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

    fun loadFolders() {

    }
}