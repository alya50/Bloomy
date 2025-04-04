package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import bloomy.composeapp.generated.resources.Res
import bloomy.composeapp.generated.resources.add_folder
import org.jetbrains.compose.resources.painterResource

@Composable
fun NewFolderButton(
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        content = {
            Image(
                painter = painterResource(resource = Res.drawable.add_folder),
                contentDescription = null,
                modifier = Modifier
                    .width(39.dp)
                    .height(39.dp)
                    .alpha(0.4f)
            )
        }
    )
}