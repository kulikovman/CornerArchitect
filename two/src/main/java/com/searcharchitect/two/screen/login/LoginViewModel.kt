package com.searcharchitect.two.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<LoginState>(LoginState.Default)
    val state: LiveData<LoginState> get() = _state


    fun onClickSignIn(login: String, password: String) {

    }

    fun onClickEmail() {

    }

}