package com.example.searcharchitect.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.searcharchitect.base.BaseViewModel
import com.example.searcharchitect.manager.IContactManager
import com.example.searcharchitect.navigation.INavigator
import com.example.searcharchitect.repositiry.INetworkRepository
import com.example.searcharchitect.retrofit.Failure
import com.example.searcharchitect.utility.extension.combine
import com.example.searcharchitect.utility.helper.ITextHelper
import com.example.searcharchitect.utility.helper.IToastHelper
import com.example.searcharchitect.utility.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val navigator: INavigator,
    private val contactManager: IContactManager,
    private val network: INetworkRepository,
    private val toast: IToastHelper,
    private val text: ITextHelper
) : BaseViewModel() {

    val city = MutableLiveData("")

    val specialization = MutableLiveData("")

    val name = MutableLiveData("")


    val items = MutableLiveData(emptyList<ItemSearchUi>())


    val isCityClearButtonVisibility = city.map { it.isNotEmpty() }

    val isSpecializationClearButtonVisibility = specialization.map { it.isNotEmpty() }

    val isNameClearButtonVisibility = name.map { it.isNotEmpty() }

    val isSearchFieldsEnabled = contactManager.getContacts().map { it.isNotEmpty() }

    val isLoading = MutableLiveData(false)

    val isNothingFound = combine(
        contactManager.getContacts(), items, isLoading
    ) { contacts, items, isLoading ->
        isLoading == false && contacts?.isNotEmpty() == true && items?.isEmpty() == true
    }

    val isMissingData = combine(
        contactManager.getContacts(), items, isLoading
    ) { contacts, items, isLoading ->
        isLoading == false && contacts?.isEmpty() == true && items?.isEmpty() == true
    }


    init {
        items.value = contactManager.getFilteredContacts()
    }


    fun onClickClearCity() {
        city.value = ""
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
                    city = city.value,
                    specialization = specialization.value,
                    name = name.value
                )
            }

            val searchResult = searchDeferred!!.await()
            log("Search result: ${searchResult.size} items")

            items.value = searchResult
            isLoading.value = false
        }
    }

    fun onClickLoadData() {
        viewModelScope.launch {
            isLoading.value = true
            network.getDataVersion().either(::handleFailure) { version ->
                viewModelScope.launch {
                    network.getContactList().either(::handleFailure) { contactList ->
                        viewModelScope.launch {
                            contactManager.updateAppData(version, contactList)
                            items.value = contactManager.getFilteredContacts()
                            isLoading.value = false
                        }
                    }
                }
            }
        }
    }

    private fun handleFailure(failure: Failure) {
        isLoading.value = false

        when (failure) {
            is Failure.ConnectionError -> toast.showLong(text.connectionError())
            is Failure.UnknownError -> toast.showLong(text.unknownError())
        }
    }

}