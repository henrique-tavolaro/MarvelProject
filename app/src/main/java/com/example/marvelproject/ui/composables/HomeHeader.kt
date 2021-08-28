package com.example.marvelproject.ui.composables


import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelproject.ui.MarvelViewModel
import com.example.marvelproject.ui.theme.Red
import com.example.marvelproject.ui.theme.appFontFamily

@ExperimentalComposeUiApi
@Composable
fun HomeHeader(
    viewModel: MarvelViewModel,
    editText: String,
    context: Context
) {

    val keyboardController = LocalSoftwareKeyboardController.current
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
                        viewModel.searchCharacter(editText, context)
                        keyboardController?.hide()
                    }),
                singleLine = true
            )
        }
    }
}