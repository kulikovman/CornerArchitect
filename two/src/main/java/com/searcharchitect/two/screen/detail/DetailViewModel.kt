package com.searcharchitect.two.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.searcharchitect.common.manager.IContactManager
import com.searcharchitect.two.screen.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val contactManager: IContactManager
) : ViewModel() {

    private var previousState: DetailState = DetailState.Default()

    private val _state = MutableLiveData<DetailState>(DetailState.Default())
    val state: LiveData<DetailState> get() = _state

    init {
        contactManager.getSelectedContact().value?.let { contact ->
            changeState(DetailState.Default(contact))
        }
    }

    private fun changeState(state: DetailState) {
        previousState = _state.value ?: DetailState.Default()
        _state.value = state
    }

}