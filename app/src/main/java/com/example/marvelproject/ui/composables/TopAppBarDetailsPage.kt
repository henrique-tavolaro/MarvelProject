package com.example.marvelproject.ui.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun TopAppBarDetailsPage(
    onClick: () -> Unit
){
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(
                onClick = {
                    onClick()
                },
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    )
}
