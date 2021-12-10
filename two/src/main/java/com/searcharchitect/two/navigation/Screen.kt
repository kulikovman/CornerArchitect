package com.searcharchitect.two.navigation

sealed class Screen(val route: String) {

    object Splash : Screen(route = "loading_screen")
    object Login : Screen(route = "login_screen")
    object Search: Screen(route = "search_screen")
    object Detail: Screen(route = "detail_screen")
    object Info: Screen(route = "info_screen")

}