package com.example.cornerarchitect.ui.contact

import androidx.lifecycle.MutableLiveData
import com.example.cornerarchitect.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(

) : BaseViewModel() {

    val contactItems = MutableLiveData(listOf<ItemContactUi>())


    fun onClickItemPosition(position: Int) {

    }

}