package com.example.searcharchitect.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.searcharchitect.base.BaseViewModel
import com.example.searcharchitect.manager.IContactManager
import com.example.searcharchitect.navigation.INavigator
import com.example.searcharchitect.utility.extension.combine
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


    // todo Сделать запрос списка через менеджер с показом лоадера
    // todo При повторном запросе отменять предыдущий
    val searchItems = combine(
        contactManager.contacts, city, specialization, name
    ) { contacts, city, specialization, name ->
        contacts?.map { contact ->
            ItemSearchUi(
                id = contact.id,
                name = "${contact.surname} ${contact.name}",
                city = contact.city,
                specialization = contact.specialization
            )
        }?.filter { contact ->
            contact.city.contains(city.orEmpty(), true)
                    && contact.specialization.contains(specialization.orEmpty(), true)
                    && contact.name.contains(name.orEmpty(), true)
        } ?: emptyList()
    }

    val isCityClearButtonVisibility = city.map { it.isNotEmpty() }

    val isSpecializationClearButtonVisibility = specialization.map { it.isNotEmpty() }

    val isNameClearButtonVisibility = name.map { it.isNotEmpty() }

    val isLoading = MutableLiveData(false)


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

    fun updateSearchList() {
        searchDeferred?.cancel()

        viewModelScope.launch {
            /*if (searchValue.length >= MIN_SEARCH_VALUE_LENGTH) {
                isPlaceListLoading.value = true

                searchDeferred = async { searchPlacesInDatabase(searchValue) }

                val searchResult = searchDeferred!!.await()
                Logg.d { "Search result: ${searchResult.size} items" }

                searchItems.value = searchResult

                if (searchResult.isEmpty()) {
                    showNothingFoundError()
                } else hideNothingFoundError()
            } else {
                hideNothingFoundError()
                searchItems.value = emptyList()
            }

            isPlaceListLoading.value = false*/
        }
    }

}