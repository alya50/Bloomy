package org.bloomy.project.screens.home.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.bloomy.project.core.theme.InterMd
import org.bloomy.project.screens.home.presentation.view.textEditorVisualTransformer.TextEditorVisualTransformer


@Composable
fun TextEditor(
    readOnly: Boolean,
) {
    var text by remember {
        mutableStateOf(
            TextFieldValue(
                "# Heading" +
                        "\n **Bold** " +
                        "\n #Hashtag" +
                        "\n --Strikethrough--" +
                        "\n ``Monospace``" +
                        "\n ~~Italics~~" +
                        "\n Normal text"

            )
        )
    }

    val fontFamily = InterMd()

    val visualTransformation = remember(readOnly) {
        TextEditorVisualTransformer(
            monospaceFontFamily = fontFamily,
            boldFontFamily = fontFamily,
            italicFontFamily = fontFamily,
            strikethroughFontFamily = fontFamily,
            headingFontFamily = fontFamily,
            hashtagFontFamily = fontFamily,
            minTextSize = 22,
            readOnly = readOnly
        )
    }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
        },
        readOnly = readOnly,
        visualTransformation = visualTransformation,
        textStyle = TextStyle(
            fontFamily = fontFamily,
            fontSize = 22.sp
        ),
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 50.dp, bottom = 50.dp, start = 10.dp, end = 10.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
            .widthIn(max = 600.dp)

    )
}
