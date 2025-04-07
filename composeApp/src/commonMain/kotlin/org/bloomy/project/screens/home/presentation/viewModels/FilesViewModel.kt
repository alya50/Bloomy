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
import org.bloomy.project.screens.home.domain.model.FileAndItsFiles
import org.bloomy.project.screens.home.domain.model.FilesAction
import org.bloomy.project.screens.home.domain.model.FilesState
import java.io.File

class FilesViewModel : ViewModel() {
    private val _state = MutableStateFlow(FilesState())
    val state = _state
        .onStart {
            searchDirectories()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: FilesAction) {
        when (action) {
            is FilesAction.ReloadFiles -> {
                searchDirectories()
            }

            is FilesAction.RenameFile -> {

            }

            is FilesAction.CloseTab -> {}
            is FilesAction.SelectTab -> {}
        }
    }

    private fun searchDirectories() {
        viewModelScope.launch(Dispatchers.IO) {
            val rootFile = AppFolders.markdownFiles
            val rootDirectoryContents = mutableListOf<FileAndItsFiles>()

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
        parentDirectoryContents: MutableList<FileAndItsFiles>,
        files: Array<File>,
    ) {
        for (file in files) {
            if (file.isFile) {
                val fileName = file.name

                // if the file is a markdown file, add it to the current folder
                if (file.isFile && fileName.endsWith(".md"))
                    parentDirectoryContents.add(FileAndItsFiles(file.path))

            }

            // if the file is a directory, search it recursively
            if (file.isDirectory) {
                val directory = FileAndItsFiles(
                    path = file.path,
                    files = mutableListOf(),
                )


                file.listFiles()?.let { files ->
                    searchDirectoriesRecursively(
                        parentDirectoryContents = directory.files!!,
                        files = files,
                    )
                }

                parentDirectoryContents.add(directory)
            }

        }
    }
}