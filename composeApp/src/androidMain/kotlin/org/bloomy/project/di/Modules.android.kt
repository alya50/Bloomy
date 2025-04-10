package org.bloomy.project.di

import org.bloomy.project.core.shared.AppFolders
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single {
        AppFolders(androidApplication())
    }
}