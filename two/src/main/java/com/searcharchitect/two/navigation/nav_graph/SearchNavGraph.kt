package com.searcharchitect.two.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.searcharchitect.two.navigation.Screen
import com.searcharchitect.two.screen.detail.DetailScreen
import com.searcharchitect.two.screen.search.SearchScreen

fun NavGraphBuilder.searchNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.Search.route,
        route = SEARCH_GRAPH_ROUTE
    ){
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }

        composable(route = Screen.Detail.route) {
            DetailScreen()
        }
    }
}