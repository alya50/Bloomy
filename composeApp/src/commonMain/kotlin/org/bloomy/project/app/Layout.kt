package org.bloomy.project.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.bloomy.project.core.theme.OutfitFontFamily
import org.bloomy.project.core.theme.ScreenSize
import org.bloomy.project.core.theme.observeScreenSize

@Composable
fun RootLayout(
    content: @Composable () -> Unit
) {
    Layout(
        content = {
            val coroutineScope = rememberCoroutineScope()
            val screenSize = ScreenSize.collectAsStateWithLifecycle()

            LaunchedEffect(true) {
                observeScreenSize(coroutineScope)
            }

           Box (
               modifier = Modifier
                   .fillMaxSize()
           ){
               BasicText(
                   text = "${screenSize.value.first} x ${screenSize.value.second}",
                   style = TextStyle(
                       fontSize = 10.sp,
                       fontFamily = OutfitFontFamily(),
                       color = Color.Black,
                       fontWeight = FontWeight.Normal
                   ),
                   modifier = Modifier
                       .zIndex(3f)
                       .offset(x = 2.dp, y = 2.dp) // Offset from the top-left corner
                       .align(Alignment.TopStart)
               )

               content()
           }

        },
        measurePolicy = { measurables, constraints ->
            // Use the max width and height from the constraints
            val width = constraints.maxWidth
            val height = constraints.maxHeight

            ScreenSize.value = Pair(width, height)

            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }

            layout(width, height) {
                var yPosition = 0
                placeables.forEach { placeable ->
                    placeable.placeRelative(x = 0, y = yPosition)
                    yPosition += placeable.height
                }
            }
        }
    )
}