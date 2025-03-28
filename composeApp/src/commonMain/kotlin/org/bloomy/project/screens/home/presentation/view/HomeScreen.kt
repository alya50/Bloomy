package org.bloomy.project.screens.home.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
        ) {
            Text(
                "Lorem ipsum",
                fontFamily = GeistFontFamily(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 50.sp,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )

            Text(
                "Lorem ipsum Lorem is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially",
                fontFamily = InterMd(),
                fontWeight = FontWeight.Normal,
                fontSize = 23.sp
            )
        }
    }
}