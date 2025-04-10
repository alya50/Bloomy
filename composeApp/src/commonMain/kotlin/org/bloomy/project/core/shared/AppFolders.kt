@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.bloomy.project.core.shared

import java.io.File

expect class AppFolders {
    val generalAppData: File
    val markdownFiles: File
}