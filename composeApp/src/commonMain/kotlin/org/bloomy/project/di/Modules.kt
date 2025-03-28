package org.bloomy.project.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.bloomy.project.navigation.main.presentation.DrawerLogicViewModel

expect val platformModule : Module

val sharedModule = module {
    viewModelOf(::DrawerLogicViewModel)
}