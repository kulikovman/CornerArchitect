package com.example.searcharchitect.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.searcharchitect.base.BaseViewModel
import com.example.searcharchitect.manager.IContactManager
import com.example.searcharchitect.navigation.INavigator
import com.example.searcharchitect.utility.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val navigator: INavigator,
    private val contactManager: IContactManager
) : BaseViewModel() {

    val city = MutableLiveData("")

    val specialization = MutableLiveData("")

    val name = MutableLiveData("")


    val searchItems = MutableLiveData(emptyList<ItemSearchUi>())


    val isCityClearButtonVisibility = city.map { it.isNotEmpty() }

    val isSpecializationClearButtonVisibility = specialization.map { it.isNotEmpty() }

    val isNameClearButtonVisibility = name.map { it.isNotEmpty() }

    val isLoading = MutableLiveData(false)


    init {
        searchItems.value = contactManager.getContactList()
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
        searchItems.value?.getOrNull(position)?.let { searchItems ->
            contactManager.contacts.value?.find { it.id == searchItems.id }?.let { contact ->
                log("Selected contact: $contact")
                contactManager.selectedContact.value = contact
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
                contactManager.getContactList(
                    city = city.value,
                    specialization = specialization.value,
                    name = name.value
                )
            }

            val searchResult = searchDeferred!!.await()
            log("Search result: ${searchResult.size} items")

            searchItems.value = searchResult
            isLoading.value = false
        }
    }

}