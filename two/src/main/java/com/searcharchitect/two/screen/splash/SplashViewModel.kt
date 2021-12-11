package com.searcharchitect.two.screen.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<SplashState>(SplashState.UpdateChecking)
    val splash: LiveData<SplashState> get() = _state


    fun onClickTryAgain() {

    }

}