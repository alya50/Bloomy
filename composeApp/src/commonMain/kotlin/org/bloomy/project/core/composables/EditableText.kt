package org.bloomy.project.core.composables

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.bloomy.project.core.theme.OutfitFontFamily
import java.awt.SystemColor.text

@Composable
fun EditableText(
    value: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit = {},
    editing: Boolean = false,
    setIsDone: () -> Unit,
    fontFamily: FontFamily = OutfitFontFamily(),
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    singleLine: Boolean = true,
    keyboardActions: KeyboardActions,
    color: Color = Color(0xb8000000),
    modifier: Modifier = Modifier,
) {

    if (!editing)
        Text(
            text = value,
            fontFamily = fontFamily,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = color,
            maxLines = if (singleLine) 1 else Int.MAX_VALUE,
            modifier = modifier
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            onClick()
                        },
                    )
                },
        )
    else {
        val focusRequester = remember { FocusRequester() }
        val coroutineScope = rememberCoroutineScope()
        var focusJob by remember { mutableStateOf<Job?>(null) }
        var textFieldValueState by remember {
            mutableStateOf(
                TextFieldValue(
                    text = value,
                    selection = TextRange(value.length)
                )
            )
        }

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        BasicTextField(
            value = textFieldValueState,
            onValueChange = {
                focusJob?.cancel()
                focusJob = coroutineScope.launch {
                    delay(3000)
                    setIsDone()
                }
                textFieldValueState = it
                onValueChange(textFieldValueState.text)
            },
            singleLine = singleLine,
            textStyle = TextStyle(
                fontFamily = fontFamily,
                fontSize = fontSize,
                fontWeight = fontWeight,
                color = color,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusJob?.cancel()
                    setIsDone()
                },
                onSend = {
                    focusJob?.cancel()
                    setIsDone()
                },
                onGo = {
                    focusJob?.cancel()
                    setIsDone()
                }
            ),
            modifier = modifier
                .focusRequester(focusRequester)
                .onKeyEvent { event ->
                    if (event.key == Key.Enter){
                        focusJob?.cancel()
                        setIsDone()
                        true
                    }

                    false
                }

        )
    }
}