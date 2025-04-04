package org.bloomy.project.screens.home.presentation.view.textEditorVisualTransformer

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.sp
import org.bloomy.project.screens.home.domain.model.FormatterIndexes
import org.bloomy.project.screens.home.domain.model.ItalicFormatterResult

/**
 * Finds Italic formatter indexes
 * @param str String to find indexes in
 * @param formatter Formatter to find indexes for
 * @param formatterLength Length of formatter
 * @return List of start and end indexes
 */
fun findItalicFormatterIndexes(str: String): ItalicFormatterResult {
    val formatter = '*'
    val formatterContentIndexes = ArrayList<FormatterIndexes>(str.length / 4) // Pre-sized ArrayList
    val formatterSpecifiersIndexes = ArrayList<Pair<Int, Int>>(str.length / 4)

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

            // Valid italic formatting requires 1 or 3+ formatters on each side

            if (count == 1 && endCount == 1) {
                formatterContentIndexes.add(FormatterIndexes(formatterStart, contentEnd ))
                formatterSpecifiersIndexes.add(Pair(formatterStart - count, contentEnd - 1))
            }

            if (count >= 3 && endCount >= 3) {
                formatterContentIndexes.add(FormatterIndexes(formatterStart, contentEnd))
                formatterSpecifiersIndexes.add(Pair(formatterStart - 3, contentEnd + 1))
            }

            continue
        }
        i++
    }

    return ItalicFormatterResult(
        formattedContents = formatterContentIndexes,
        specifiers = formatterSpecifiersIndexes
    )
}


fun findItalicFormatter(str: String) = findItalicFormatterIndexes(str)

private fun addFormatterSpecifierStyle(builder: AnnotatedString.Builder, start: Int, end:Int, readOnly: Boolean, textSize: Int) {
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


fun transformItalics(
    text: AnnotatedString,
    italicFontFamily: FontFamily,
    textSize: Int,
    readOnly: Boolean,
): Transformation {
    val matches = findItalicFormatter(text.text)

    return if (matches.formattedContents.isNotEmpty()) {
        val builder = AnnotatedString.Builder(text)
        for ((start, end) in matches.formattedContents) {
            builder.addStyle(
                style = SpanStyle(
                    fontStyle = FontStyle.Italic,
                    fontFamily = italicFontFamily,
                    fontSize = textSize.sp
                ),
                start,
                end
            )
        }

        for ((start, end) in matches.specifiers) {
            addFormatterSpecifierStyle(builder, start, start + 1 , readOnly, textSize)
            addFormatterSpecifierStyle(builder, end + 1 , end + 2, readOnly, textSize)
        }

        Transformation(
            annotatedString = builder.toAnnotatedString(),
            offsetMapping = OffsetMapping.Identity
        )
    } else
        Transformation(annotatedString = text, offsetMapping = OffsetMapping.Identity)
}
