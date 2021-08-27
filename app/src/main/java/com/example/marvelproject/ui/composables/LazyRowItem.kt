package com.example.marvelproject.ui.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelproject.MarvelViewModel
import com.example.marvelproject.ui.theme.Red
import com.example.marvelproject.ui.theme.appFontFamily

@Composable
fun LazyRowItem(
    pageNumber: Int,
    page: MutableState<Int>,
    viewModel: MarvelViewModel,
    nameSearch: MutableState<String>

){

    val color by animateColorAsState(
        targetValue = if (pageNumber == page.value) Red else Color.White,
        animationSpec = tween(durationMillis = 250)
    )

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .height(36.dp)
            .width(36.dp)
            .clickable {
                page.value = pageNumber
                viewModel.searchCharacter(nameSearch.value)
            },
        backgroundColor = color,
        border = BorderStroke(1.dp, color = Red),
        shape = CircleShape
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = pageNumber.toString(),
                modifier = Modifier.padding(5.dp),
                fontFamily = appFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
            )
        }
    }
}