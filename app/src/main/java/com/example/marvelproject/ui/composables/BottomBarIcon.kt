package com.example.marvelproject.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.marvelproject.MarvelViewModel
import com.example.marvelproject.ui.theme.Red

@Composable
fun BottomBarIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String
){
    IconButton(onClick = onClick) {
        Icon(
            modifier = modifier,
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Red
        )
    }
}