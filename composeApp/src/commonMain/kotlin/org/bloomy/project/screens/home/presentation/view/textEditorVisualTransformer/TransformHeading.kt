package org.bloomy.project.screens.home.presentation.view.textEditorVisualTransformer

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.sp

 val HEADING_REGEX_PATTERN = Regex(pattern = "\\#{1,4}\\s([^\n\\#]*)")

fun transformHeading(
    text: AnnotatedString,
    headingFontFamily: FontFamily,
    minTextSize: Int,
    readOnly: Boolean,
): Transformation {
    val matches = HEADING_REGEX_PATTERN.findAll(text.text)
    val sizeList = listOf(
        (minTextSize + 25).sp,
        (minTextSize + 20).sp,
        (minTextSize + 15).sp,
        (minTextSize + 5).sp
    )

    return if (matches.count() > 0) {
        val builder = AnnotatedString.Builder(text)
        for (match in matches) {
            val matchRange = match.range
            val headingLevel = getHeadingLevel(match.value)


            builder.addStyle(
                style = SpanStyle(
                    fontSize = if (readOnly) 0.sp else sizeList[headingLevel - 1]
                ),
                matchRange.first,
                matchRange.first + headingLevel
            )

            builder.addStyle(
                style = SpanStyle(
                    fontSize = if (readOnly) 0.sp else sizeList[headingLevel - 1]
                ),
                matchRange.first + headingLevel +1 ,
                matchRange.first + headingLevel + 1
            )

            builder.addStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = sizeList[headingLevel - 1],
                    fontFamily = headingFontFamily
                ),
                matchRange.first + headingLevel, matchRange.last + 1 // Adjusted to end of match
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


private fun getHeadingLevel(text: String): Int {
    var i = 0
    while (i < text.length) {
        if (text[i] == '#') {
            i++
        } else {
            break
        }
    }
    return i
}