package com.example.cornerarchitect.ui.specialization

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SpecializationViewModel : ViewModel() {

    val specializationItems = MutableLiveData(listOf<ItemSpecializationUi>())


}