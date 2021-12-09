package com.searcharchitect.two.screen.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {

    val viewState = searchViewModel.searchState.observeAsState()

    when (viewState.value) {
        SearchState.Default -> {}
        SearchState.Progress -> {}
        SearchState.Result -> {}
        SearchState.Empty -> {}
    }

}