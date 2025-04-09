package org.bloomy.project.screens.home.presentation.view.chat

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.bloomy.project.core.theme.Screen
import org.bloomy.project.core.theme.ScreenSize
import org.bloomy.project.core.theme.ScreenSizeName

val colorStops = arrayOf(
    0.2f to Color(0xfffcd5a4),
    0.4f to Color(0xfffcd5a4),
    1f to Color(0xffffc787),
)

@Composable
fun Chat(
    isChatOpen: Boolean = false,
    onOpenChat: () -> Unit,
    onCloseChat: () -> Unit
) {
    val screenName = ScreenSizeName.collectAsStateWithLifecycle()
    val ScreenSize = ScreenSize.collectAsStateWithLifecycle()
    val targetTranslation = if (isChatOpen) {
        0f
    } else {
        when (screenName.value) {
            Screen.SM -> ScreenSize.value.first.toFloat()
            else -> 400f
        }
    }

    val animatedTranslation = animateFloatAsState(
        targetValue = targetTranslation,
        label = "chat-slide"
    )

    val panelWidth = when (screenName.value) {
        Screen.SM -> if (isChatOpen) Modifier.fillMaxSize() else Modifier.width(0.dp)
        else -> if (isChatOpen) Modifier.width(400.dp) else Modifier.width(0.dp)
    }

    val roundedCornerShape = when (screenName.value) {
        Screen.SM -> Modifier
        else -> Modifier.shadow(
            5.dp,
            shape = RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp)
        )
    }

    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Column(
            modifier = Modifier
                .then(panelWidth)
                .graphicsLayer {
                    translationX = animatedTranslation.value
                }
                .then(roundedCornerShape)
                .background(Brush.verticalGradient(colorStops = colorStops))
                .fillMaxHeight()
        ) {
            // Chat content here
            // You might want to add a close button
        }

        if (!isChatOpen) {
            // Add your chat open button here
            // ChatOpenButton(onClick = onOpenChat)
        }
    }
}