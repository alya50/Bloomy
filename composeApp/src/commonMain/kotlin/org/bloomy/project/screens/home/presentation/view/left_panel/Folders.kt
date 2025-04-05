@file:OptIn(ExperimentalMaterial3Api::class)

package org.bloomy.project.screens.home.presentation.view.left_panel

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bloomy.composeapp.generated.resources.Res
import bloomy.composeapp.generated.resources.right
import kotlinx.coroutines.flow.MutableStateFlow
import org.bloomy.project.core.composables.SinglelineTextField
import org.bloomy.project.core.theme.OutfitFontFamily
import org.bloomy.project.screens.home.domain.model.FileData
import org.bloomy.project.screens.home.domain.model.LeftPanelAction
import org.jetbrains.compose.resources.painterResource
import java.io.File

@Composable
fun FilesView(
    files: MutableList<FileData>,
    onAction: (LeftPanelAction) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(lifecycleState) {
        if (lifecycleState == Lifecycle.State.RESUMED) {
            onAction(LeftPanelAction.onDirectoryRefresh)
        }
    }

    Column {
        for (file in files) {
            if (file.current.isDirectory)
                DirectoryView(
                    name = file.current.name,
                    files = file.directoryContents,
                    onAction = onAction,
                    file = file.current,
                )
            else
                File(
                    name = file.current.name,
                )
        }
    }
}


@Composable
fun DirectoryView(
    name: String,
    file: File,
    files: List<FileData>?,
    onAction: (LeftPanelAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)

    ) {
        val isOpen = remember { mutableStateOf(false) }

        DirectoryName(
            name = name,
            onClick = { isOpen.value = !isOpen.value },
            isOpen = isOpen.value,
            onRename = { prevName, newName ->
                onAction(LeftPanelAction.onFolderRename(file, newName))
            }
        )

        if (isOpen.value)
            Column(
                modifier = Modifier
                    .padding(start = 13.dp),
                verticalArrangement = Arrangement.spacedBy(1.dp),
            ) {
                if (files != null)
                    for (file in files) {
                        if (file.current.isDirectory)
                            DirectoryView(
                                name = file.current.name,
                                files = file.directoryContents!!,
                                onAction = onAction,
                                file = file.current,
                            )
                        else
                            File(
                                name = file.current.name,
                            )
                    }
            }
    }
}

@Composable
fun DirectoryName(
    name: String,
    onClick: () -> Unit,
    isOpen: Boolean,
    onRename: (prevName: String, newName: String) -> Unit,
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

                val readOnly = remember { MutableStateFlow(true) }
                val fileName = remember(name) { MutableStateFlow(name) }

                if (readOnly.value)
                    Text(
                        text = name,
                        fontFamily = OutfitFontFamily(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xb8000000),
                        modifier = Modifier
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = { onClick() },
                                    onDoubleTap = {
                                        readOnly.value = !readOnly.value
                                    }
                                )
                            }
                    )
                else
                    SinglelineTextField(
                        value = name,
                        onValueChange = {
                            if (name != it)
                                fileName.value = it
                        },
                        keyboardActions = KeyboardActions(
                            onDone = {
                                readOnly.value = true
                                onRename(fileName.value, fileName.value)
                            },
                        ),

                        textStyle = TextStyle(
                            fontFamily = OutfitFontFamily(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xb8000000),
                        ),
                        modifier = Modifier
                            .background(Color(0x1f000000))
                    )
            }
        }
    }
}

@Composable
fun File(
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
            SinglelineTextField(
                value = name,
                onValueChange = { },
                textStyle = TextStyle(
                    fontFamily = OutfitFontFamily(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0x8e000000),
                ),
            )
        }
    }
}