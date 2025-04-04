package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import bloomy.composeapp.generated.resources.Res
import bloomy.composeapp.generated.resources.sort_descending
import org.jetbrains.compose.resources.painterResource

@Composable
fun SortButton() {
    Image(
        painter = painterResource(resource = Res.drawable.sort_descending),
        contentDescription = null,
        modifier = Modifier
            .width(44.dp)
            .height(44.dp)
            .alpha(0.4f)
    )
}