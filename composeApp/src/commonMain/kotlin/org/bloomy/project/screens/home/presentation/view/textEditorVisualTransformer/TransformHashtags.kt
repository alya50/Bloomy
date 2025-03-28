package org.bloomy.project.screens.home.presentation.view.textEditorVisualTransformer

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.unit.sp

val HASHTAGS_REGEX_PATTERN = Regex(pattern = "(#[A-Za-z0-9-_]+)(?:#[A-Za-z0-9-_]+)*")

fun transformHashtags(
    text: AnnotatedString,
    hashtagFontFamily: FontFamily,
    textSize: Int,
    readOnly: Boolean,
): Transformation {
    val builder = AnnotatedString.Builder(text)
    val matches = HASHTAGS_REGEX_PATTERN.findAll(text.text)
    for (match in matches) {
        val matchRange = match.range
        builder.addStyle(
            style = SpanStyle(
                color = Color(0xFF4285F4),
                fontFamily = hashtagFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = textSize.sp
            ), start = matchRange.first, end = matchRange.last + 1
        )
    }

    return Transformation(
        annotatedString = builder.toAnnotatedString(),
        offsetMapping = OffsetMapping.Identity
    )
}
