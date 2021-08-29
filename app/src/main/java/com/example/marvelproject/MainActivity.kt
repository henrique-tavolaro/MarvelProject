package com.example.marvelproject

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelproject.model.Result
import com.example.marvelproject.ui.MarvelViewModel
import com.example.marvelproject.ui.navigation.Screen
import com.example.marvelproject.ui.pages.DetailsPage
import com.example.marvelproject.ui.pages.HomePage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MarvelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
                composable(Screen.HomeScreen.route) {
                    HomePage(
                        viewModel = viewModel,
                        onClick = {
                            navController.currentBackStackEntry?.arguments =
                                Bundle().apply {
                                    putParcelable("result", it)
                                }
                            navController.navigate(
                                Screen.DetailsScreen.route
                            )
                        },
                        context = this@MainActivity
                    )
                }
                composable(Screen.DetailsScreen.route) {
                    val rememberedResult = remember { mutableStateOf<Result?>(null) }
                    val result = navController.previousBackStackEntry
                        ?.arguments?.getParcelable<Result>("result") ?: rememberedResult.value
                    rememberedResult.value = result
                    DetailsPage(
                        result = result ?: throw IllegalArgumentException("parcelable was null"),
                        onClick = { navController.popBackStack() })
                }
            }
        }

    }
}