package org.bloomy.project.screens.home.presentation.view.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.bloomy.project.screens.home.presentation.view.TextEditor

@Composable
fun RowScope.Editor() {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .weight(1f)
    ) {
        TextEditor(
            readOnly = true
        )
    }
}
