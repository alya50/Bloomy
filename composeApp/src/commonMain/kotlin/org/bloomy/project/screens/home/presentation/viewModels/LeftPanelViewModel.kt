package org.bloomy.project.screens.home.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.bloomy.project.core.shared.AppFolders
import org.bloomy.project.screens.home.domain.model.FileData
import org.bloomy.project.screens.home.domain.model.LeftPanelAction
import org.bloomy.project.screens.home.domain.model.LeftPanelState
import java.io.File


class LeftPanelViewModel : ViewModel() {
    private val _state = MutableStateFlow(LeftPanelState())
    val state = _state
        .onStart {
            searchDirectories()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: LeftPanelAction) {
        when (action) {
            is LeftPanelAction.onDirectoryRefresh -> {
                searchDirectories()
            }

            is LeftPanelAction.onFolderRename -> {
                try {
                    viewModelScope.launch(Dispatchers.IO) {
                        val file = action.file
                        val newName = action.newName

                        file.renameTo(
                            File(file.absolutePath.replaceAfterLast("/", newName))
                        )

                        searchDirectories()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
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

    private fun searchDirectories() {
        viewModelScope.launch(Dispatchers.IO) {

            val rootFile = AppFolders.markdownFiles

            val rootDirectoryContents = mutableListOf<FileData>()


            for (file in rootFile.listFiles()!!) {
                println(file.name)
            }

            searchDirectoriesRecursively(rootDirectoryContents, rootFile.listFiles()!!)

            println(rootDirectoryContents)
            _state.value = _state.value.copy(
                files = rootDirectoryContents
            )
        }
    }

    /**
     * Recursively searches for directories and files in the given directory
     */
    private fun searchDirectoriesRecursively(
        parentDirectoryContents: MutableList<FileData>,
        files: Array<File>,
    ) {
        for (file in files) {
            if (file.isFile) {
                val fileName = file.name

                // if the file is a markdown file, add it to the current folder
                if (file.isFile && fileName.endsWith(".md")) {
                    parentDirectoryContents.add(FileData(file))
                }
            }

            // if the file is a directory, search it recursively
            if (file.isDirectory) {
                val directory = FileData(
                    current = file,
                    directoryContents = mutableListOf(),
                )

                file.listFiles()?.let {
                    searchDirectoriesRecursively(
                        directory.directoryContents!!,
                        it
                    )
                }

                parentDirectoryContents.add(directory)
            }

        }
    }
}