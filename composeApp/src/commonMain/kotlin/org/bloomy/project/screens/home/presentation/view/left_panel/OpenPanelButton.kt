package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bloomy.composeapp.generated.resources.Res
import bloomy.composeapp.generated.resources.left_panel
import org.bloomy.project.screens.home.domain.model.LeftPanelAction
import org.jetbrains.compose.resources.painterResource

@Composable
fun OpenPanelButton(
    onLeftPanelAction: (LeftPanelAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(30.dp)
            .shadow(5.dp, shape = RoundedCornerShape(5.dp))
            .background(Color(0xffffcd91))
            .clickable {
                onLeftPanelAction(LeftPanelAction.openLeftPanel)
            }
    ) {
        Image(
            painter = painterResource(resource = Res.drawable.left_panel),
            contentDescription = null,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .alpha(0.4f)

        )
    }
}