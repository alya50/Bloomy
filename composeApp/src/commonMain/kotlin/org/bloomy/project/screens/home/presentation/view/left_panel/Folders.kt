@file:OptIn(ExperimentalMaterial3Api::class)

package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bloomy.composeapp.generated.resources.Res
import bloomy.composeapp.generated.resources.right
import org.bloomy.project.core.theme.OutfitFontFamily
import org.bloomy.project.screens.home.domain.model.Folder
import org.jetbrains.compose.resources.painterResource

@Composable
fun Folders(
    folders: List<Folder>,
    listState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        state = listState,
    ) {
        items(
            folders,
            key = { it.name }
        ) { folder ->
            Folder(
                name = folder.name,
                filesNames = folder.files,
            )
        }

    }
}

@Composable
fun Folder(
    name: String,
    filesNames: List<String>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)

    ) {
        val isOpen = remember { mutableStateOf(false) }

        FolderName(
            name = name,
            onClick = { isOpen.value = !isOpen.value },
            isOpen = isOpen.value
        )

        if (isOpen.value)
            Column(
                modifier = Modifier
                    .padding(start = 13.dp),
                verticalArrangement = Arrangement.spacedBy(1.dp),
            ) {
                for (fileName in filesNames) {
                    FileName(
                        name = fileName,
                    )
                }
            }
    }
}

@Composable
fun FolderName(
    name: String,
    onClick: () -> Unit,
    isOpen: Boolean,
) {
    val animatedIconRotation: Float by animateFloatAsState(
        if (isOpen) 90f else 0f,
        label = "folder_icon_rotation"
    )
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val backgroundColor = if (isHovered) Color(0x1f000000) else Color.Transparent

    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(backgroundColor)
                .padding(5.dp)
                .fillMaxWidth()
                .clickable(
                    onClick = onClick,
                    interactionSource = interactionSource,
                    indication = null
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(Res.drawable.right),
                    contentDescription = null,
                    modifier = Modifier
                        .width(25.dp)
                        .height(25.dp)
                        .rotate(animatedIconRotation)
                )
                Text(
                    text = name,
                    fontFamily = OutfitFontFamily(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xb8000000),
                )
            }
        }
    }
}

@Composable
fun FileName(
    name: String,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val backgroundColor = if (isHovered) Color(0x1f000000) else Color.Transparent

    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(backgroundColor)
                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxWidth()
                .clickable(
                    onClick = {},
                    interactionSource = interactionSource,
                    indication = null
                )
        ) {
            Text(
                text = name,
                fontFamily = OutfitFontFamily(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0x8e000000),
            )
        }
    }
}