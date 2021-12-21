package com.searcharchitect.two.screen.search

import com.searcharchitect.common.model.Contact

sealed class SearchState {

    object StartSearch : SearchState()
    object Loading : SearchState()
    data class Result(val contacts: List<Contact>): SearchState()
    object Empty : SearchState()

}
