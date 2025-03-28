package org.bloomy.project.navigation.main.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import org.bloomy.project.navigation.main.domain.models.DrawerLogicActions
import org.bloomy.project.navigation.main.domain.models.DrawerLogicState

@Composable
fun DrawerLogicRoot(
    viewModel: DrawerLogicViewModel,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    DrawerLogic(
        state = state.value,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}

@Composable
fun DrawerLogic(
    state: DrawerLogicState,
    onAction: (DrawerLogicActions) -> Unit,
) {
    val navController = rememberNavController()
    val animatedOffsetX by animateFloatAsState(
        targetValue = state.drawerOffsetX,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffcd5a4))
    ) {
        Drawer(
            zIndex = (state.drawerContentZIndex),
            minOffsetX = state.minOffsetX,
        )

        Box(
            modifier = Modifier
                .clickable(
                    onClick = { onAction(DrawerLogicActions.onMainContentClickWhenDrawerOpen) },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                )
                .zIndex(3f)
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragStart = { onAction(DrawerLogicActions.onDragStart) },
                        onDragEnd = { onAction(DrawerLogicActions.onDragEnd) },
                        onDragCancel = { onAction(DrawerLogicActions.onDragCancel) },
                        onHorizontalDrag = { change, dragAmount ->
                            change.consume()
                            onAction(DrawerLogicActions.onDrag(dragAmount))
                        },
                    )
                }
                .graphicsLayer {
                    translationX = animatedOffsetX
                    clip = true
                }
        ) {
            MenuButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
            )
            MainNavigationHost(
                navController = navController,
            )
        }
    }
}

