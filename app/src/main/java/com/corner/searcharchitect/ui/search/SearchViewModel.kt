package com.corner.searcharchitect.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.corner.searcharchitect.base.BaseViewModel
import com.corner.searcharchitect.manager.IContactManager
import com.corner.searcharchitect.navigation.INavigator
import com.corner.searcharchitect.utility.extension.combine
import com.corner.searcharchitect.utility.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val navigator: INavigator,
    private val contactManager: IContactManager,
) : BaseViewModel() {

    val location = MutableLiveData("")

    val specialization = MutableLiveData("")

    val name = MutableLiveData("")

    val items = MutableLiveData(emptyList<ItemSearchUi>())

    val isLocationClearButtonVisibility = location.map { it.isNotEmpty() }

    val isSpecializationClearButtonVisibility = specialization.map { it.isNotEmpty() }

    val isNameClearButtonVisibility = name.map { it.isNotEmpty() }

    val isSearchFieldsEnabled = contactManager.getContacts().map { it.isNotEmpty() }

    val isLoading = MutableLiveData(false)

    val isNothingFound = combine(
        location, specialization, name, items, isLoading
    ) { location, specialization, name, items, isLoading ->
        val isLocationQuery = location?.length ?: 0 >= SEARCH_QUERY_LENGTH
        val isSpecializationQuery = specialization?.length ?: 0 >= SEARCH_QUERY_LENGTH
        val isNameQuery = name?.length ?: 0 >= SEARCH_QUERY_LENGTH

        isLoading == false && (isLocationQuery || isSpecializationQuery || isNameQuery)
                && items?.isEmpty() == true
    }

    val isEmptySearchQuery = combine(
        location, specialization, name, items
    ) { location, specialization, name, items ->
        items?.isEmpty() == true && location?.isEmpty() == true
                && specialization?.isEmpty() == true && name?.isEmpty() == true
    }


    fun onClickClearLocation() {
        location.value = ""
    }

    fun onClickClearSpecialization() {
        specialization.value = ""
    }

    fun onClickClearName() {
        name.value = ""
    }

    fun onClickItemPosition(position: Int) {
        items.value?.getOrNull(position)?.let { searchItems ->
            contactManager.getContacts().value?.find { it.id == searchItems.id }?.let { contact ->
                log("Selected contact: $contact")
                contactManager.setSelectedContact(contact)

                navigator.actionSearchToDetail()
            }
        }
    }

    private var searchDeferred: Deferred<List<ItemSearchUi>>? = null

    fun updateContactList() {
        val isLocationQuery = location.value?.length ?: 0 >= SEARCH_QUERY_LENGTH
        val isSpecializationQuery = specialization.value?.length ?: 0 >= SEARCH_QUERY_LENGTH
        val isNameQuery = name.value?.length ?: 0 >= SEARCH_QUERY_LENGTH

        if (isLocationQuery || isSpecializationQuery || isNameQuery) {
            searchDeferred?.cancel()
            isLoading.value = true

            viewModelScope.launch {
                searchDeferred = async {
                    contactManager.getFilteredContacts(
                        city = location.value?.trim()
                            .takeIf { it?.length ?: 0 >= SEARCH_QUERY_LENGTH },
                        specialization = specialization.value?.trim()
                            .takeIf { it?.length ?: 0 >= SEARCH_QUERY_LENGTH },
                        name = name.value?.trim().takeIf { it?.length ?: 0 >= SEARCH_QUERY_LENGTH }
                    )
                }

                val searchResult = searchDeferred!!.await()
                log("Search result: ${searchResult.size} items")

                items.value = searchResult
                isLoading.value = false
            }
        } else {
            items.value = emptyList()
        }
    }

    fun onClickInfo() {
        navigator.actionSearchToIndoDialog()
    }


    companion object {
        const val SEARCH_QUERY_LENGTH = 3
    }

}