package org.bloomy.project.screens.home.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.bloomy.project.core.theme.GeistFontFamily
import org.bloomy.project.core.theme.InterMd
import org.bloomy.project.core.theme.OutfitFontFamily

@Composable
fun HomeScreenRoot(
) {
    HomeScreen()
}

@Composable
fun HomeScreen(

) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffde3c3))
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xfffddab0))
                .fillMaxHeight()
                .width(300.dp)
                .padding(top = 20.dp, bottom = 20.dp, start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {

            }
        }
        var readOnly by remember { mutableStateOf(true) }

        Button(
            onClick = {
                readOnly = !readOnly
            },
        ) {
            Text(text = if (readOnly) "Edit" else "Read Only")
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {

            TextEditor(
                 readOnly = readOnly
            )

        }
    }
}