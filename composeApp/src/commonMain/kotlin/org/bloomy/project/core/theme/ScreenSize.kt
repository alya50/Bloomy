package org.bloomy.project.core.theme

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val ScreenSize = MutableStateFlow(Pair(0, 0))

enum class Screen {
    SM, MD, LG, XL, XL2, XL3
}

val ScreenSizeName = MutableStateFlow(Screen.SM)

// Launch a coroutine to update _screenSizeName whenever _screenSize changes
fun observeScreenSize(scope: CoroutineScope) {
    scope.launch {
        ScreenSize.map {
            val width = it.first

            when (width) {
                in 0..600 -> Screen.SM
                in 599..840 -> Screen.MD
                in 839..1024 -> Screen.LG
                in 1023..1280 -> Screen.XL
                in 1279..1440 -> Screen.XL2
                else -> Screen.XL3
            }
        }
            .distinctUntilChanged()
            .collect { newScreenSize ->
                ScreenSizeName.value = newScreenSize
            }
    }
}