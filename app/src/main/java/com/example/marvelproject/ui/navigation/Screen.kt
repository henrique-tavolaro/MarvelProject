package com.example.marvelproject.ui.navigation

sealed class Screen(val route: String) {

    object HomeScreen : Screen(route = "homeScreen")

    object DetailsScreen : Screen(route = "detailsScreen/{result}")

}