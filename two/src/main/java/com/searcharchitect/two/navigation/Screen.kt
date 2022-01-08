package com.searcharchitect.two.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("loading_screen")
    object Login : Screen("login_screen")
    object Search : Screen("search_screen")
    object Detail : Screen("detail_screen")

}