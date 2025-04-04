package org.bloomy.project.screens.home.presentation.view.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val colorStops = arrayOf(
    0.2f to Color(0xfffcd5a4),
    0.4f to Color(0xfffcd5a4),
    1f to Color(0xffffc787),
)

@Composable
fun Chat() {
    Column(
        modifier = Modifier
            .shadow(5.dp, shape = RoundedCornerShape(topStart = 50.dp, bottomStart =50.dp))
            .background(Brush.verticalGradient(colorStops = colorStops))
            .fillMaxHeight()
            .width(400.dp)
    ) {

    }
}