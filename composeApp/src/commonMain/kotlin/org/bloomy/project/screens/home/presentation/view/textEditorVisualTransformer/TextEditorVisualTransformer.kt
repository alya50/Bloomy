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

fun findLineFormatter(str: String, formatter: Char): List<Int> {
    val indexes = mutableListOf<Int>()

    str.lines().forEachIndexed { index, line ->
        if (line.startsWith("$formatter ")) {
            indexes.add(index)
        }
    }

    return indexes
}





fun findLineFormatterByLength(str: String, formatter: Char, formatterMaxLength: Int): List<Triple<Int, Int, Int>> {
    val indexes = mutableListOf<Triple<Int, Int, Int>>()
    val paragraphSplitedText = str.split("\n").filter { it.trim().isNotEmpty().and(it != "\n") }
    println(paragraphSplitedText)

    paragraphSplitedText.forEachIndexed { lineIndex, line ->
        for (possibleFormatterLength in 1..formatterMaxLength) {
            val repeated = formatter.toString().repeat(possibleFormatterLength)
            if (line.startsWith("$repeated ")) {

                val values: Triple<Int, Int, Int> =
                    if (paragraphSplitedText.isEmpty()) continue
                    else {
                        val startIndex = str.indexOf(paragraphSplitedText[lineIndex])
                        val endIndex =
                            if (lineIndex + 1 < paragraphSplitedText.size)
                                str.indexOf(paragraphSplitedText[lineIndex]) + paragraphSplitedText[lineIndex].length
                            else str.length - 1

                        Triple(startIndex, endIndex, possibleFormatterLength)
                    }

                indexes.add(indexes.size, values)
            }
        }
    }

    return indexes
}