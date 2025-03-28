package org.bloomy.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.bloomy.project.app.App
import org.bloomy.project.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Bloomy",
    ) {
        App()
    }
}