package org.bloomy.project.screens.home.presentation.view.textEditorVisualTransformer

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.unit.sp

fun transformHeading(
    text: AnnotatedString,
    headingFontFamily: FontFamily,
    minTextSize: Int,
    readOnly: Boolean,
): Transformation {
    val matches = findLineFormatterByLength(text.text, '#', 6)
    val sizeList = listOf(
        (minTextSize + 35).sp,
        (minTextSize + 30).sp,
        (minTextSize + 25).sp,
        (minTextSize + 20).sp,
        (minTextSize + 15).sp,
        (minTextSize + 5).sp
    )

    return if (matches.isNotEmpty()) {
        val builder = AnnotatedString.Builder(text)
        for (match in matches) {
            builder.addStyle(
                style = SpanStyle(
                    fontSize = if (readOnly) 0.sp else sizeList[match.third - 1]
                ),
                match.first,
                match.first + match.third
            )

            builder.addStyle(
                style = SpanStyle(
                    fontSize = if (readOnly) 0.sp else sizeList[match.third - 1]
                ),
                match.first + match.third + 1,
                match.first + match.third + 1
            )

            builder.addStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = sizeList[match.third - 1],
                    fontFamily = headingFontFamily
                ),
                match.first + match.third, match.second + 1 // Adjusted to end of match
            )
            // Removed the styling for ending hashes
        }
        Transformation(
            annotatedString = builder.toAnnotatedString(),
            offsetMapping = OffsetMapping.Identity
        )
    } else {
        Transformation(annotatedString = text, offsetMapping = OffsetMapping.Identity)
    }
}