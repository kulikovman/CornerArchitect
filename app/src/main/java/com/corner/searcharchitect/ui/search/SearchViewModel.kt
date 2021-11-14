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
        contactManager.getContacts(), items, isLoading
    ) { contacts, items, isLoading ->
        isLoading == false && contacts?.isNotEmpty() == true && items?.isEmpty() == true
    }


    init {
        items.value = contactManager.getFilteredContacts()
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

    var searchDeferred: Deferred<List<ItemSearchUi>>? = null

    fun updateContactList() {
        searchDeferred?.cancel()
        isLoading.value = true

        viewModelScope.launch {
            searchDeferred = async {
                contactManager.getFilteredContacts(
                    city = location.value?.trim(),
                    specialization = specialization.value?.trim(),
                    name = name.value?.trim()
                )
            }

            val searchResult = searchDeferred!!.await()
            log("Search result: ${searchResult.size} items")

            items.value = searchResult
            isLoading.value = false
        }
    }

    fun onClickInfo() {
        navigator.actionSearchToIndoDialog()
    }

}