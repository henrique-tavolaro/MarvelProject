package com.example.marvelproject.ui.composables

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.marvelproject.TAG_LAZY_ROW
import com.example.marvelproject.ui.MarvelViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomBarHomePage(
    coroutineScope: CoroutineScope,
    listState: LazyListState,
    page: MutableState<Int>,
    viewModel: MarvelViewModel,
    context: Context,
    pageNumberList: List<Int>,
    nameSearch: MutableState<String>
    ) {

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
                modifier = Modifier.testTag(TAG_LAZY_ROW),
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