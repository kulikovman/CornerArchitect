package com.searcharchitect.two.screen.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.searcharchitect.common.manager.IArchitectsManager
import com.searcharchitect.common.model.Contact
import com.searcharchitect.common.repositiry.IDatastoreRepository
import com.searcharchitect.common.utility.Constant.SEARCH_QUERY_LENGTH
import com.searcharchitect.common.utility.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val datastore: IDatastoreRepository,
    private val architects: IArchitectsManager
) : ViewModel() {

    private var previousState: SearchState = SearchState.StartSearch

    private val _state = MutableLiveData(previousState)
    val state: LiveData<SearchState> get() = _state

    private val _location = mutableStateOf("")
    val location = _location

    private val _specialization = mutableStateOf("")
    val specialization = _specialization

    private val _name = mutableStateOf("")
    val name = _name

    var dataVersion = ""


    init {
        viewModelScope.launch {
            dataVersion = datastore.getSavedDataVersion().toString()
        }
    }


    private var searchDeferred: Deferred<List<Contact>>? = null

    fun updateContactList(location: String, specialization: String, name: String) {
        _location.value = location

        val isLocationQuery = location.length >= SEARCH_QUERY_LENGTH
        val isSpecializationQuery = specialization.length >= SEARCH_QUERY_LENGTH
        val isNameQuery = name.length >= SEARCH_QUERY_LENGTH

        if (isLocationQuery || isSpecializationQuery || isNameQuery) {
            searchDeferred?.cancel()
            changeState(SearchState.Loading)

            viewModelScope.launch {
                searchDeferred = async {
                    architects.getFilteredContacts(
                        location = location.trim().takeIf { it.length >= SEARCH_QUERY_LENGTH },
                        specialization = specialization.trim()
                            .takeIf { it.length >= SEARCH_QUERY_LENGTH },
                        name = name.trim().takeIf { it.length >= SEARCH_QUERY_LENGTH }
                    )
                }

                val searchResult = searchDeferred!!.await()
                log("Search result: ${searchResult.size} items")

                if (searchResult.isNotEmpty()) {
                    changeState(SearchState.Result(searchResult))
                } else changeState(SearchState.Empty)
            }
        } else {
            changeState(SearchState.StartSearch)
        }
    }

    fun saveContact(contact: Contact) {
        architects.setSelectedContact(contact)
    }

    private fun changeState(state: SearchState) {
        previousState = _state.value ?: SearchState.StartSearch
        _state.value = state
    }

}