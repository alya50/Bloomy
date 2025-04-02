package org.bloomy.project.screens.home.presentation.view.textEditorVisualTransformer

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.sp
import org.bloomy.project.screens.home.domain.model.BoldFormatterResult
import org.bloomy.project.screens.home.domain.model.FormatterIndexes


/**
 * Finds bold formatter indexes
 * @param str String to find indexes in
 * @param formatter Formatter to find indexes for
 * @param formatterLength Length of formatter
 * @return List of start and end indexes
 */
private fun findBoldFormatterIndexes(str: String): BoldFormatterResult {
    val formatter = '*'
    val formatterContentIndexes = ArrayList<FormatterIndexes>(str.length / 4) // Pre-sized ArrayList
    val formatterSpecifiersIndexes = ArrayList<Pair<Pair<Int, Int>, Pair<Int, Int>>>(str.length / 4)

    var i = 0
    while (i < str.length) {
        // Count consecutive formatters
        var count = 0
        while (i < str.length && str[i] == formatter) {
            count++
            i++
        }

        // If we found formatters and there's content after them
        if (count > 0 && i < str.length) {
            val formatterStart = i

            // Find the ending formatter
            while (i < str.length && str[i] != formatter) {
                i++
            }

            // Count ending formatters
            var endCount = 0
            val contentEnd = i
            while (i < str.length && str[i] == formatter) {
                endCount++
                i++
            }

            // Valid italic formatting requires 2 formatters on each side
            if (count >= 2) {
                formatterContentIndexes.add(FormatterIndexes(formatterStart, contentEnd))
                formatterSpecifiersIndexes.add(Pair(Pair(formatterStart - 2 , formatterStart), Pair(contentEnd, contentEnd + 2)))
            }
            continue
        }
        i++
    }

    return BoldFormatterResult(
        formattedContents = formatterContentIndexes,
        specifiers = formatterSpecifiersIndexes
    )
}

private fun addFormatterSpecifierStyle(builder: AnnotatedString.Builder, start: Int, end: Int, readOnly: Boolean, textSize: Int) {
    builder.addStyle(
        style = SpanStyle(
            color = Color.Gray,
            baselineShift = BaselineShift.Superscript,
            fontSize = if (readOnly) 0.sp else textSize.sp,
        ),
        start,
        end
    )

}

fun transformBold(
    text: AnnotatedString,
    boldFontFamily: FontFamily,
    textSize: Int,
    readOnly: Boolean,
): Transformation {
    val matches = findBoldFormatterIndexes(text.text)

    return if (matches.formattedContents.isNotEmpty()) {
        val builder = AnnotatedString.Builder(text)

        for (specifiers in matches.specifiers) {
            addFormatterSpecifierStyle(builder, specifiers.first.first, specifiers.first.second, readOnly, textSize)
            addFormatterSpecifierStyle(builder, specifiers.second.first, specifiers.second.second, readOnly, textSize)
        }

        for (match in matches.formattedContents) {
            builder.addStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = boldFontFamily,
                    fontSize = textSize.sp
                ), match.start, match.end
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

