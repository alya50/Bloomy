package org.bloomy.project.navigation.main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import org.bloomy.project.core.theme.OutfitFontFamily
import kotlin.math.abs

@Composable
fun Drawer(
    zIndex: Float,
    minOffsetX: Float? = null,
) {
    val maxWidth = abs(minOffsetX ?: 0f).dp
    println("maxWidth=$maxWidth")

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .zIndex(zIndex)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(maxWidth)
        )
        {
            Column(
                modifier =  Modifier
                    .fillMaxSize()
//                    .clickable(
//                        onClick = {
//                            println("drawer clicked")
//                        },
//                        interactionSource = remember { MutableInteractionSource() },
//                        indication = null,
//                    )
,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Drawer",
                    fontSize = 30.sp,
                    color = Color.White,
                    fontFamily = OutfitFontFamily(),
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
