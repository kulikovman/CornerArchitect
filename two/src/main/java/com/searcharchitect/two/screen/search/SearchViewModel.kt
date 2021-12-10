package com.searcharchitect.two.screen.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<SearchState>(SearchState.Default)
    val state: LiveData<SearchState> get() = _state


}