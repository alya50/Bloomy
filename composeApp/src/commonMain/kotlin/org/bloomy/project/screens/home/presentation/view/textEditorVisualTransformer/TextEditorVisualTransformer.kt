package org.bloomy.project.screens.home.presentation.view.textEditorVisualTransformer

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class TextEditorVisualTransformer(
    val italicFontFamily: FontFamily,
    val boldFontFamily: FontFamily,
    val strikethroughFontFamily: FontFamily,
    val monospaceFontFamily: FontFamily,
    val headingFontFamily: FontFamily,
    val hashtagFontFamily: FontFamily,
    val minTextSize: Int,
    val readOnly: Boolean
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        var transformation = transformBold(
            text = text,
            boldFontFamily = boldFontFamily,
            textSize = minTextSize,
            readOnly = readOnly
        )

        transformation =
            transformItalics(
                text = transformation.annotatedString,
                italicFontFamily = italicFontFamily,
                textSize = minTextSize,
                readOnly = readOnly
            )

        transformation =
            transformHashtags(
                text = transformation.annotatedString,
                hashtagFontFamily = hashtagFontFamily,
                textSize = minTextSize,
                readOnly = readOnly
            )

        transformation =
            transformHeading(
                text = transformation.annotatedString,
                headingFontFamily = headingFontFamily,
                minTextSize = minTextSize,
                readOnly = readOnly
            )

        transformation = transformStrikeThrough(
            text = transformation.annotatedString,
            strikethroughFontFamily = strikethroughFontFamily,
            textSize = minTextSize,
            readOnly = readOnly
        )

        transformation =
            transformMonospace(
                text = transformation.annotatedString,
                monospaceFontFamily = monospaceFontFamily,
                textSize = minTextSize,
                readOnly = readOnly
            )

        return convertToTransformedText(transformation)
    }

    private fun convertToTransformedText(transformation: Transformation): TransformedText {
        return TransformedText(
            text = transformation.annotatedString,
            offsetMapping = transformation.offsetMapping
        )
    }
}

data class Transformation(val annotatedString: AnnotatedString, val offsetMapping: OffsetMapping)
