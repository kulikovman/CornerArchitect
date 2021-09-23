package com.example.cornerarchitect.ui.detail

import androidx.lifecycle.MutableLiveData
import com.example.cornerarchitect.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(

) : BaseViewModel() {

    val name = MutableLiveData("")

    val surname = MutableLiveData("")

    val specialization = MutableLiveData("")

    val work1 = MutableLiveData("")

    val work2 = MutableLiveData("")

    val phone = MutableLiveData("")

    val email = MutableLiveData("")

}