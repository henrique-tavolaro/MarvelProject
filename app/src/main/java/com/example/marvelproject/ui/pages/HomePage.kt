package com.example.marvelproject.ui.pages

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.marvelproject.MarvelViewModel
import com.example.marvelproject.ui.composables.CircularIndicator
import com.example.marvelproject.ui.composables.LazyColumnItemTile
import com.example.marvelproject.ui.fragments.HomeFragmentDirections
import com.example.marvelproject.ui.theme.MarvelProjectTheme
import com.example.marvelproject.ui.theme.Red
import com.example.marvelproject.ui.theme.appFontFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomePage(
    viewModel: MarvelViewModel,
    navController: NavController
) {

    val loading = viewModel.loading
    val keyboardController = LocalSoftwareKeyboardController.current
    val editText = viewModel.textSearch.value
    val pageNumberList = viewModel.pageNumberList.value
    val result = viewModel.result.value
    val page = viewModel.page
    val nameSearch = viewModel.nameSearch
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    MarvelProjectTheme() {
        Scaffold(
            bottomBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, bottom = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        viewModel.previusPage()
                    }) {
                        Icon(
                            modifier = Modifier
                                .size(150.dp)
                                .weight(1f),
                            imageVector = Icons.Filled.ArrowLeft,
                            contentDescription = "Arrow back icon",
                            tint = Red
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(2f)
                            .padding(horizontal = 20.dp)
                    ) {

                        LazyRow(
                            state = listState
                        ) {
                            items(items = pageNumberList) {

                                val color by animateColorAsState(
                                    targetValue = if (it == page.value) Red else Color.White,
                                    animationSpec = tween(durationMillis = 250)
                                )

                                Card(
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                        .height(36.dp)
                                        .width(36.dp)
                                        .clickable {
                                            page.value = it
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
                                            text = it.toString(),
                                            modifier = Modifier.padding(5.dp),
                                            fontFamily = appFontFamily,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 20.sp,
                                        )
                                    }
                                }
                            }
                        }
                    }


                    IconButton(onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(
                                index = page.value - 2,
                                scrollOffset = page.value
                            )
                        }
                        viewModel.nextPage()
                    }) {
                        Icon(
                            modifier = Modifier
                                .size(150.dp)
                                .weight(1f),
                            imageVector = Icons.Filled.ArrowRight,
                            contentDescription = "Arrow back icon",
                            tint = Red
                        )
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Column(
                        modifier = Modifier.padding(
                            horizontal = 36.dp,
                            vertical = 12.dp
                        ),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row {
                            Text(
                                text = "BUSCA MARVEL",
                                fontFamily = appFontFamily,
                                fontWeight = FontWeight.Black,
                                fontSize = 16.sp,
                                color = Red
                            )
                            Text(
                                text = "TESTE FRONT-END",
                                fontFamily = appFontFamily,
                                fontWeight = FontWeight.Light,
                                fontSize = 16.sp,
                                color = Red
                            )
                        }
                        Divider(
                            thickness = 2.dp,
                            modifier = Modifier
                                .padding(bottom = 12.dp)
                                .width(55.dp),
                            color = Red
                        )
                        Text(
                            text = "Nome do Personagem",
                            fontFamily = appFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = Red
                        )
                        OutlinedTextField(
                            value = editText,
                            onValueChange = viewModel::onTextSearchChanged,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    viewModel.resetSearchParams()
                                    viewModel.searchCharacter(editText)
                                    keyboardController?.hide()
                                }),
                            singleLine = true
                        )
                    }
                    Text(
                        modifier = Modifier
                            .background(Red)
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 120.dp),
                        text = "Nome",
                        fontFamily = appFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    CircularIndicator(loading = loading.value)

                    LazyColumn() {
                        items(items = result) {
                            LazyColumnItemTile(
                                result = it,

                            onClick = {
                                navController.navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it))

                            }
                            )
                            Divider(
                                thickness = 1.dp,
                                modifier = Modifier
                                    .padding(start = 2.dp)
                                    .fillMaxWidth(),
                                color = Red
                            )
                        }
                    }
                }
            }
        }
    }
}