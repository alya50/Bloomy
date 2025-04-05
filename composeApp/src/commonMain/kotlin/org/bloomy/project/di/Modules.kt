package org.bloomy.project.di

import org.bloomy.project.screens.home.presentation.viewModels.LeftPanelViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule : Module

val sharedModule = module {
    viewModelOf(::LeftPanelViewModel)
}