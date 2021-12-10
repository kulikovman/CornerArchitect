package com.searcharchitect.two.screen.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.searcharchitect.two.screen.search.view.SearchDefault
import com.searcharchitect.two.screen.search.view.SearchEmpty
import com.searcharchitect.two.screen.search.view.SearchProgress
import com.searcharchitect.two.screen.search.view.SearchResult

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {
    val viewState = searchViewModel.state.observeAsState()

    when (viewState.value) {
        SearchState.Default -> SearchDefault(
            onClickInfo = {}
        )
        SearchState.Progress -> SearchProgress(
            locationText = "",
            specializationText = "",
            nameText = "",
            onClickInfo = {}
        )
        SearchState.Result -> SearchResult(
            locationText = "",
            specializationText = "",
            nameText = "",
            contacts = emptyList(),
            onClickInfo = {}
        )
        SearchState.Empty -> SearchEmpty(
            locationText = "",
            specializationText = "",
            nameText = "",
            onClickInfo = {}
        )
    }
}