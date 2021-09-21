package com.example.cornerarchitect.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CityViewModel : ViewModel() {

    val cityItems = MutableLiveData(listOf<ItemCityUi>())


}