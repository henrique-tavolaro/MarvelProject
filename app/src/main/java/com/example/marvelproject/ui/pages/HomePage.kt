package com.example.marvelproject.ui.pages

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.marvelproject.ui.MarvelViewModel
import com.example.marvelproject.ui.composables.*
import com.example.marvelproject.ui.fragments.HomeFragmentDirections
import com.example.marvelproject.ui.theme.MarvelProjectTheme
import com.example.marvelproject.ui.theme.Red
import com.example.marvelproject.ui.theme.appFontFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomePage(
    viewModel: MarvelViewModel,
    navController: NavController,
    context: Context
) {

    val loading = viewModel.loading
    val editText = viewModel.textSearch.value
    val pageNumberList = viewModel.pageNumberList.value
    val result = viewModel.result.value
    val page = viewModel.page
    val nameSearch = viewModel.nameSearch
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    MarvelProjectTheme() {


        Surface() {
            Scaffold(
                bottomBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, end = 30.dp, bottom = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BottomBarIcon(
                            modifier = Modifier
                                .size(150.dp)
                                .weight(1f),
                            onClick = {
                                coroutineScope.launch {
                                    listState.animateScrollToItem(
                                        index = page.value - 2,
                                        scrollOffset = page.value
                                    )
                                }
                                viewModel.previusPage(context = context)
                            },
                            icon = Icons.Filled.ArrowLeft,
                            contentDescription = "Arrow back icon"
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 50.dp)
                        ) {

                            LazyRow(
                                state = listState
                            ) {
                                items(items = pageNumberList) { pageNumber ->

                                    LazyRowItem(
                                        pageNumber = pageNumber,
                                        page = page,
                                        viewModel = viewModel,
                                        nameSearch = nameSearch,
                                        context = context
                                    )
                                }
                            }
                        }
                        BottomBarIcon(
                            modifier = Modifier
                                .size(150.dp)
                                .weight(1f),
                            onClick = {
                                coroutineScope.launch {
                                    listState.animateScrollToItem(
                                        index = page.value - 3,
                                        scrollOffset = page.value
                                    )
                                }
                                viewModel.nextPage(context = context)
                            },
                            icon = Icons.Filled.ArrowRight,
                            contentDescription = "Arrow forward icon"
                        )

                    }
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        HomeHeader(
                            viewModel = viewModel,
                            editText = editText,
                            context = context
                        )
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
                                        navController.navigate(
                                            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                                                it
                                            )
                                        )
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
}