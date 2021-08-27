package com.example.marvelproject.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.example.marvelproject.DEFAULT_RECTANGLE_IMAGE
import com.example.marvelproject.model.Result
import com.example.marvelproject.ui.composables.loadImageUri
import com.example.marvelproject.ui.theme.MarvelProjectTheme
import com.example.marvelproject.ui.theme.appFontFamily

@Composable
fun DetailsPage(
    result: Result?,
    navController: NavController) {

    MarvelProjectTheme() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                      navController.popBackStack()
                            },
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = null)
                        }
                    }
                )
            }
        ) {
            Column() {
                result!!.thumbnail.let {
                    val url ="${it.path}.${it.extension}"
                    val image =
                        loadImageUri(
                            url = url.toUri(),
                            defaultImage = DEFAULT_RECTANGLE_IMAGE
                        ).value
                    image?.let { img ->
                        Image(
                            bitmap = img.asImageBitmap(),
                            modifier = Modifier
                                .height(300.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
                    text = result.name,
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp)
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = result.description,
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp
                )
            }

        }



    }



}