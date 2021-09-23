package com.example.cornerarchitect.ui.specialization

import androidx.lifecycle.MutableLiveData
import com.example.cornerarchitect.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpecializationViewModel @Inject constructor(

) : BaseViewModel() {

    val specializationItems = MutableLiveData(listOf<ItemSpecializationUi>())


    fun onClickItemPosition(position: Int) {

    }

}