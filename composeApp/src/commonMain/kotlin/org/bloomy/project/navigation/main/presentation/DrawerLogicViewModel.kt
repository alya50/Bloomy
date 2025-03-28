package org.bloomy.project.navigation.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.bloomy.project.core.theme.ScreenSize
import org.bloomy.project.navigation.main.domain.models.DrawerLogicActions
import org.bloomy.project.navigation.main.domain.models.DrawerLogicState

class DrawerLogicViewModel : ViewModel() {
    private var firstTime = true
    private val _state = MutableStateFlow(DrawerLogicState())

    val state = _state
        .onStart {
            if (firstTime) observeScreenSize()
            firstTime = false
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _state.value
        )

    fun onAction(action: DrawerLogicActions) {
        when (action) {
            is DrawerLogicActions.closeDrawer -> closeDrawer()
            is DrawerLogicActions.openDrawer -> openDrawer()
            is DrawerLogicActions.onDragStart -> onDragStart()
            is DrawerLogicActions.onDragEnd -> onDragEnd()
            is DrawerLogicActions.onDragCancel -> onDragCancel()
            is DrawerLogicActions.onDrag -> onDrag(action.dragAmount)
            is DrawerLogicActions.onMainContentClickWhenDrawerOpen -> closeDrawer()
        }
    }

    private fun onDrag(dragAmount: Float) {
        val minOffsetX = state.value.minOffsetX

        minOffsetX?.let { minOffset ->
            val newOffset = (state.value.drawerOffsetX + dragAmount)
                .coerceIn(minOffset, 0f)

            println("onDrag: dragAmount=$dragAmount, newOffset=$newOffset, minOffset=$minOffset")
            println("onDrag: current state=${state.value}")

            if (newOffset <= 0) {
                _state.update {
                    it.copy(
                        drawerOffsetX = newOffset,
                        drawerContentZIndex = 1f,
                    ).also { newState ->
                        println("onDrag: updated state=$newState")
                    }
                }
            }
        }
    }

    private fun onDragEnd() {
        val dragThreshold = when (ScreenSize.value.first) {
            in 0..600 -> 10
            in 601..840 -> 30
            else -> 40
        }

        println("onDragEnd: starting with state=${state.value}")

        if (state.value.isDrawerOpen) {
            val minOffsetX = state.value.minOffsetX
            minOffsetX?.let { minOffset ->
                val isEnoughToClose = state.value.drawerOffsetX > minOffset + dragThreshold
                println("onDragEnd (drawer open): isEnoughToClose=$isEnoughToClose")
                println("onDragEnd (drawer open): offsetX=${state.value.drawerOffsetX}, dragStartX=${state.value.dragStartX}")

                if (isEnoughToClose) {
                    closeDrawer()
                } else {
                    openDrawer()
                }
            }
        } else {
            val minOffsetX = state.value.minOffsetX
            minOffsetX?.let { minOffset ->
                val isEnoughToOpen = state.value.drawerOffsetX < -(dragThreshold)

                println("onDragEnd (drawer closed): isEnoughToOpen=$isEnoughToOpen")
                println("onDragEnd (drawer closed): offsetX=${state.value.drawerOffsetX}, dragStartX=${state.value.dragStartX}")

                if (isEnoughToOpen) {
                    openDrawer()
                } else {
                    closeDrawer()
                }
            }
        }
    }

    private fun onDragCancel() {
//        println("onDragCancel: starting with state=${state.value}")
//        val maxOffsetX = state.value.maxOffsetX
//
//        _state.update {
//            it.copy(
//                isDragging = false,
//                drawerOffsetX = if (it.isDrawerOpen) maxOffsetX else 0f,
//                isContentUnderDrawer = false,
//            ).also { newState ->
//                println("onDragCancel: updated state=$newState")
//            }
//        }
    }


    private fun onDragStart() {
        println("onDragStart: starting with state=${state.value}")
        _state.update {
            it.copy(
                isDragging = true,
                dragStartX = it.drawerOffsetX,
                drawerContentZIndex = 1f,
            ).also { newState ->
                println("onDragStart: updated state=$newState")
            }
        }
    }

    private fun closeDrawer() {
        println("closeDrawer: starting with state=${state.value}")
        _state.update {
            it.copy(
                isDragging = false,
                isDrawerOpen = false,
                drawerOffsetX = 0f,
                drawerContentZIndex = 1f,
            ).also { newState ->
                println("closeDrawer: updated state=$newState")
            }
        }
    }

    private fun openDrawer() {
        println("openDrawer: starting with state=${state.value}")
        state.value.minOffsetX?.let { minOffsetX ->
            _state.update {
                it.copy(
                    isDrawerOpen = true,
                    drawerOffsetX = minOffsetX,
                    drawerContentZIndex = 4f
                ).also { newState ->
                    println("openDrawer: updated state=$newState")
                }
            }
        }
    }

    private fun observeScreenSize() {
        println("observeScreenSize: starting collection")
        viewModelScope.launch {
            ScreenSize.collect { screenSize ->
                println("observeScreenSize: received new size=$screenSize")
                _state.update {
                    val newMinOffsetX = when (screenSize.first) {
                        in 0..600 -> -(screenSize.first.toFloat())
                        in 601..840 -> -(screenSize.first / 2f)
                        else -> -400f
                    }

                    it.copy(
                        minOffsetX = newMinOffsetX,
                        drawerOffsetX = if (it.isDrawerOpen) newMinOffsetX else 0f,
                    ).also { newState ->
                        println("observeScreenSize: updated state=$newState")
                    }
                }
            }
        }
    }
}

