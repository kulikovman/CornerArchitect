package com.searcharchitect.two.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.searcharchitect.two.screen.search.SearchState
import javax.inject.Inject

class DetailViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<DetailState>(DetailState.Default)
    val state: LiveData<DetailState> get() = _state

}