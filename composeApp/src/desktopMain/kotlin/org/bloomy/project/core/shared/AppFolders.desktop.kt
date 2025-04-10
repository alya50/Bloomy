package org.bloomy.project.core.shared

import java.io.File


actual class AppFolders {
    actual val generalAppData: File by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val folderName = "bloomy"

        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), folderName)
            os.contains("mac") -> File(userHome, "Library/Application Support/$folderName")
            else -> File(userHome, ".local/share/$folderName")
        }

        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        appDataDir
    }

    actual val markdownFiles: File by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        val markdownFolder = File(generalAppData, "markdown")

        if (!markdownFolder.exists()) {
            markdownFolder.mkdirs()

            val file = File(markdownFolder, "example.md")
            file.writeText("# Title\n\n**Bold**\n*Italic*\n~~Strikethrough~~\n\nSome text")
        }

        markdownFolder
    }
}