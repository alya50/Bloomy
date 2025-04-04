package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val colorStops = arrayOf(
    0.2f to Color(0xffffd198),
    0.4f to Color(0xfffdd9b0),
    1f to Color(0xfffdd9b0),
)

@Composable
fun LeftPanel() {
    Column(
        modifier = Modifier
            .shadow(10.dp, shape = RoundedCornerShape(topEnd = 40.dp), ambientColor = Color(0x71000000))
            .background(Brush.verticalGradient(colorStops = colorStops))
            .fillMaxHeight()
            .width(350.dp)
            .padding(top = 20.dp, bottom = 20.dp, start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {

        }
    }

}