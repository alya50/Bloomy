package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import bloomy.composeapp.generated.resources.Res
import bloomy.composeapp.generated.resources.search
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchButton() {
    Image(
        painter = painterResource(resource = Res.drawable.search),
        contentDescription = null,
        modifier = Modifier
            .width(34.dp)
            .height(34.dp)
            .alpha(0.4f)
    )
}