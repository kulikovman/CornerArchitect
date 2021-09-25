package com.example.cornerarchitect.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.cornerarchitect.base.BaseViewModel
import com.example.cornerarchitect.manager.IContactManager
import com.example.cornerarchitect.utility.extension.combine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val contact: IContactManager
) : BaseViewModel() {

    val search = MutableLiveData("")

    private val cities = contact.contacts.map { contacts ->
        val architectCount = mutableMapOf<String, Int>()
        contacts.forEach { contact ->
            contact.city.forEach { city ->
                architectCount[city] = if (architectCount.containsKey(city)) {
                    architectCount.getValue(city) + 1
                } else 1
            }
        }

        architectCount.map { (city, count) ->
            ItemCityUi(
                name = city,
                quantity = count.toString()
            )
        }.sortedByDescending { it.quantity }
    }

    val cityItems = combine(cities, search) { cities, search ->
        cities?.filter { city -> city.name.contains(search.orEmpty()) } ?: emptyList()
    }


    fun onClickItemPosition(position: Int) {

    }

}