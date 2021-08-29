package com.example.marvelproject.ui.pages

import android.content.Context
import android.os.Bundle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.marvelproject.TAG_LAZY_COLUMN
import com.example.marvelproject.TAG_LAZY_ROW
import com.example.marvelproject.model.Result
import com.example.marvelproject.ui.MarvelViewModel
import com.example.marvelproject.ui.composables.*
import com.example.marvelproject.ui.navigation.Screen
import com.example.marvelproject.ui.theme.MarvelProjectTheme
import com.example.marvelproject.ui.theme.Red
import com.example.marvelproject.ui.theme.appFontFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomePage(
    viewModel: MarvelViewModel,
    onClick: (result: Result) -> Unit,
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
                    BottomBarHomePage(
                        coroutineScope = coroutineScope,
                        listState = listState,
                        page = page,
                        viewModel = viewModel,
                        context = context,
                        pageNumberList = pageNumberList,
                        nameSearch = nameSearch
                    )
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

                        LazyColumn(
                            modifier = Modifier
                                .testTag(TAG_LAZY_COLUMN)
                        ) {
                            items(items = result) {
                                LazyColumnItemTile(
                                    result = it,
                                    onClick = { it ->
                                        onClick(it)
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