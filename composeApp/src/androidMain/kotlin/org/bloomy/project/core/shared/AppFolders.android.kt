package org.bloomy.project.core.shared

import android.content.Context
import java.io.File

actual class AppFolders(
    private val context: Context
) {
    val appContext = context.applicationContext
    actual val generalAppData: File = appContext.dataDir

    actual val markdownFiles: File by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        val markdownFolder = File(generalAppData, "markdown")

        if (!markdownFolder.exists()) {
            markdownFolder.mkdirs()

            val exampleFolder = File(markdownFolder, "example")
            exampleFolder.mkdirs()

            val file = File(exampleFolder, "example.md")
            file.writeText("# Title\n\n**Bold**\n*Italic*\n~~Strikethrough~~\n\nSome text")
        }

        markdownFolder
    }
}