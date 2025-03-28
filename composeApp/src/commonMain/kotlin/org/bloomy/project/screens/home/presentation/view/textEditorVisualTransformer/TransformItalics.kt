package org.bloomy.project.screens.home.presentation.view.textEditorVisualTransformer

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.sp

val ITALICS_REGEX_PATTERN = Regex(pattern = "(\\~{2})(\\s*\\b)([^\\*]*)(\\b\\s*)(\\~{2})")

fun transformItalics(
    text: AnnotatedString,
    italicFontFamily: FontFamily,
    textSize: Int,
    readOnly: Boolean,
): Transformation {
    val matches = ITALICS_REGEX_PATTERN.findAll(text.text)

    return if (matches.count() > 0) {
        val builder = AnnotatedString.Builder(text)
        for (match in matches) {
            val matchRange = match.range

            builder.addStyle(
                style = SpanStyle(
                    color = Color.Gray,
                    fontFamily = italicFontFamily,
                    baselineShift = BaselineShift.Superscript,
                    fontSize = if (readOnly) 0.sp else textSize.sp
                ),
                matchRange.first,
                matchRange.first + 2
            )

            builder.addStyle(
                style = SpanStyle(
                    color = Color.Gray,
                    baselineShift = BaselineShift.Superscript,
                    fontSize = if (readOnly) 0.sp else textSize.sp
                ),
                matchRange.last - 1,
                matchRange.last + 1
            )

            builder.addStyle(
                style = SpanStyle(
                    fontStyle = FontStyle.Italic,
                    fontFamily = italicFontFamily,
                    fontSize = textSize.sp
                ),
                matchRange.first + 2,
                matchRange.last - 1
            )
        }

        Transformation(
            annotatedString = builder.toAnnotatedString(),
            offsetMapping = OffsetMapping.Identity
        )
    } else
        Transformation(annotatedString = text, offsetMapping = OffsetMapping.Identity)
}
