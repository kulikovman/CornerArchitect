package com.searcharchitect.two.screen.search

sealed class SearchState {

    object Default : SearchState()
    object Progress : SearchState()
    object Result : SearchState()
    object Empty : SearchState()

}
