package com.example.cornerarchitect.ui.city

import androidx.lifecycle.MutableLiveData
import com.example.cornerarchitect.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(

) : BaseViewModel() {

    val cityItems = MutableLiveData(listOf<ItemCityUi>())


    fun onClickItemPosition(position: Int) {

    }

}