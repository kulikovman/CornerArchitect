package com.example.cornerarchitect.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {

    val name = MutableLiveData("")

    val surname = MutableLiveData("")

    val specialization = MutableLiveData("")

    val work1 = MutableLiveData("")

    val work2 = MutableLiveData("")

    val phone = MutableLiveData("")

    val email = MutableLiveData("")

}