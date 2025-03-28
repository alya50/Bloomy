package org.bloomy.project.navigation.main.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bloomy.composeapp.generated.resources.Res
import bloomy.composeapp.generated.resources.menu
import org.jetbrains.compose.resources.painterResource

@Composable
fun MenuButton(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xffe0e0e0))

    ) {
        Image(
            painter = painterResource(Res.drawable.menu),
            contentDescription = "Menu",
            modifier = modifier,
        )
    }
}
