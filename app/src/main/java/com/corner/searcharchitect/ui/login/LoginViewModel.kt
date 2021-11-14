package com.corner.searcharchitect.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.corner.searcharchitect.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : BaseViewModel() {

    val username = MutableLiveData("")

    val password = MutableLiveData("")

    val isUsernameClearButtonVisibility = username.map { it.isNotEmpty() }

    val isPasswordClearButtonVisibility = password.map { it.isNotEmpty() }


    fun onClickClearUsername() {
        username.value = ""
    }

    fun onClickClearPassword() {
        password.value = ""
    }

    fun onClickSignIn() {

    }

}