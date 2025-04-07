package org.bloomy.project.core.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout
import org.bloomy.project.core.theme.ScreenSize

/**
 * Composable that allows to place content in a specific position.
 */
@Composable
fun InPosition(
    x: Int,
    y: Int,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        measurePolicy = { measurables, constraints ->
            // Use the max width and height from the constraints
            val width = constraints.maxWidth
            val height = constraints.maxHeight

            ScreenSize.value = Pair(width, height)

            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }

            layout(width, height) {
                placeables.forEach { placeable ->
                    placeable.place(x, y)
                }
            }
        }
    )
}