package com.example.cornerarchitect.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.cornerarchitect.base.BaseViewModel
import com.example.cornerarchitect.manager.IContactManager
import com.example.cornerarchitect.navigation.INavigator
import com.example.cornerarchitect.utility.extension.combine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val navigator: INavigator,
    private val contactManager: IContactManager
) : BaseViewModel() {

    val search = MutableLiveData("")

    private val cities = contactManager.contacts.map { contacts ->
        val count = mutableMapOf<String, Int>()
        contacts.forEach { contact ->
            contact.city.forEach { city ->
                count[city] = if (count.containsKey(city)) {
                    count.getValue(city) + 1
                } else 1
            }
        }

        count.map { (city, count) ->
            ItemCityUi(
                name = city,
                quantity = count.toString()
            )
        }.sortedByDescending { it.quantity.toIntOrNull() }

        // todo Сделать двойную сортировку -> количество -> город
    }

    val cityItems = combine(cities, search) { cities, search ->
        cities?.filter { city ->
            city.name.contains(search.orEmpty(), true)
        } ?: emptyList()

        // todo При поиске не учитывать регистр
    }

    val isSearchVisibility = MutableLiveData(true)


    fun onClickClearSearch() {
        search.value = ""
    }

    fun onClickItemPosition(position: Int) {
        cityItems.value?.getOrNull(position)?.let { cityItem ->
            contactManager.selectedCity = cityItem.name
            navigator.actionCityToSpecialization()
        }
    }

}