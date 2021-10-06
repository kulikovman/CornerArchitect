package com.example.cornerarchitect.ui.person

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.cornerarchitect.base.BaseViewModel
import com.example.cornerarchitect.manager.IContactManager
import com.example.cornerarchitect.navigation.INavigator
import com.example.cornerarchitect.utility.extension.combine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val navigator: INavigator,
    private val contactManager: IContactManager
) : BaseViewModel() {

    val search = MutableLiveData("")

    private val people = contactManager.contacts.map { contacts ->
        contacts.filter { contact ->
            contact.city.contains(contactManager.selectedCity)
                    && contact.specialization.contains(contactManager.selectedSpecialization)
        }.map {
            ItemPeopleUi(
                id = it.id,
                name = it.name,
                work = it.work.orEmpty(),
                position = it.position.orEmpty()
            )
        }.sortedBy { it.name }
    }

    val peopleItems = combine(people, search) { people, search ->
        people?.filter { person ->
            person.name.contains(search.orEmpty())
        } ?: emptyList()
    }


    fun onClickItemPosition(position: Int) {
        peopleItems.value?.getOrNull(position)?.let { peopleItems ->
            contactManager.contacts.value?.find { it.id == peopleItems.id }?.let { contact ->
                contactManager.selectedContact = contact
                navigator.actionPeopleToDetail()
            }
        }
    }

}