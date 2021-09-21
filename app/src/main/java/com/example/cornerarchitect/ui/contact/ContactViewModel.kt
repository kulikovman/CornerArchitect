package com.example.cornerarchitect.ui.contact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {

    val contactItems = MutableLiveData(listOf<ItemContactUi>())


}