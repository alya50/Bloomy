package org.bloomy.project

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import org.bloomy.project.screens.home.presentation.view.HomeScreen

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}


@Composable
fun BaselineShiftExample() {
    Column {
        Text(
            text = buildAnnotatedString {
                append("Normal text ")
                withStyle(style = SpanStyle(baselineShift = BaselineShift.Superscript)) {
                    append("Superscript")
                }
            }
        )

        Text(
            text = buildAnnotatedString {
                append("Normal text ")
                withStyle(style = SpanStyle(baselineShift = BaselineShift.Subscript)) {
                    append("Subscript")
                }
            }
        )

        Text(
            text = "Custom shift: Text",
        )

        Text(
            text = buildAnnotatedString {
                append("Custom shift: ")
                withStyle(style = SpanStyle(baselineShift = BaselineShift(-0.5f))) {
                    append("Text")
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewBaselineShiftExample() {
    BaselineShiftExample()
}


@Preview
@Composable
fun ColorExample() {

    Text(
        text = buildAnnotatedString {
            append("Hello")
            // push green text style so that any appended text will be green
            pushStyle(SpanStyle(color = Color.Green))
            // append new text, this text will be rendered as green
            append(" World")
            // pop the green style
            pop(0)
            // append a string without style
            append("!")
            // then style the last added word as red, exclamation mark will be red
            addStyle(SpanStyle(color = Color.Red), "Hello World".length, this.length)

            toAnnotatedString()
        }
    )
}