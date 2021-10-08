package com.example.cornerarchitect.ui.specialization

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.cornerarchitect.base.BaseViewModel
import com.example.cornerarchitect.manager.IContactManager
import com.example.cornerarchitect.navigation.INavigator
import com.example.cornerarchitect.utility.extension.combine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpecializationViewModel @Inject constructor(
    private val navigator: INavigator,
    private val contactManager: IContactManager
) : BaseViewModel() {

    val search = MutableLiveData("")

    private val specializations = contactManager.contacts.map { contacts ->
        val count = mutableMapOf<String, Int>()

        contacts.filter { contact ->
            contact.city.contains(contactManager.selectedCity)
        }.forEach { contact ->
            contact.specialization.forEach { specialization ->
                count[specialization] = if (count.containsKey(specialization)) {
                    count.getValue(specialization) + 1
                } else 1
            }
        }

        count.map { (city, count) ->
            ItemSpecializationUi(
                name = city,
                quantity = count.toString()
            )
        }.sortedByDescending { it.quantity }

        // todo Сделать двойную сортировку -> количество -> специализация
    }

    val specializationItems = combine(specializations, search) { specializations, search ->
        specializations?.filter { specialization ->
            specialization.name.contains(search.orEmpty(), true)
        } ?: emptyList()
    }

    val isSearchVisibility = MutableLiveData(false)


    fun onClickBack() {
        navigator.goBack()
    }

    fun onClickClearSearch() {
        search.value = ""
    }

    fun onClickItemPosition(position: Int) {
        specializationItems.value?.getOrNull(position)?.let { specializationItem ->
            contactManager.selectedSpecialization = specializationItem.name
            navigator.actionSpecializationToPeople()
        }
    }

}