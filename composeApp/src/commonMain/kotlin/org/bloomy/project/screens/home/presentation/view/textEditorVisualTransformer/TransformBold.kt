package org.bloomy.project.screens.home.presentation.view.textEditorVisualTransformer

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.sp

fun transformBold(
    text: AnnotatedString,
    boldFontFamily: FontFamily,
    textSize: Int,
    readOnly: Boolean,
): Transformation {
    val matches = findBoldFormatter(text.text)

    return if (matches.isNotEmpty()) {
        val builder = AnnotatedString.Builder(text)
        for (match in matches) {
            builder.addStyle(
                style = SpanStyle(
                    color = Color.Gray,
                    baselineShift = BaselineShift.Superscript,
                    fontSize = if (readOnly) 0.sp else textSize.sp,
                ),
                match.first,
                match.first + 2
            )

            builder.addStyle(
                style = SpanStyle(
                    color = Color.Gray,
                    baselineShift = BaselineShift.Superscript,
                    fontSize = if (readOnly) 0.sp else textSize.sp,
                ),
                match.second - 2,
                match.second
            )

            builder.addStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = boldFontFamily,
                    fontSize = textSize.sp
                ), match.first + 2, match.second - 1
            )

        }
        Transformation(
            annotatedString = builder.toAnnotatedString(),
            offsetMapping = OffsetMapping.Identity
        )
    } else {
        Transformation(annotatedString = text, offsetMapping = OffsetMapping.Identity)
    }
}

