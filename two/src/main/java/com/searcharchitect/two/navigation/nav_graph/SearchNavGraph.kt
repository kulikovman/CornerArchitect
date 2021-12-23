package com.searcharchitect.two.navigation.nav_graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.searcharchitect.two.navigation.Screen
import com.searcharchitect.two.screen.detail.DetailScreen
import com.searcharchitect.two.screen.detail.DetailViewModel
import com.searcharchitect.two.screen.info.InfoScreen
import com.searcharchitect.two.screen.info.InfoViewModel
import com.searcharchitect.two.screen.search.SearchScreen
import com.searcharchitect.two.screen.search.SearchViewModel

fun NavGraphBuilder.searchNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.Search.route,
        route = SEARCH_GRAPH_ROUTE
    ){
        composable(
            route = Screen.Search.route
        ) {
            val searchViewModel= hiltViewModel<SearchViewModel>()
            SearchScreen(
                navController = navController,
                searchViewModel = searchViewModel
            )
        }

        composable(
            route = Screen.Detail.route
        ) {
            val detailViewModel = hiltViewModel<DetailViewModel>()
            DetailScreen(
                detailViewModel = detailViewModel
            )
        }

        composable(
            route = Screen.Info.route
        ) {
            val infoViewModel = hiltViewModel<InfoViewModel>()
            InfoScreen(
                navController = navController,
                infoViewModel = infoViewModel
            )
        }
    }
}