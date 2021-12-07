package com.searcharchitect.two.screen.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    private val _splashState = MutableLiveData<SplashState>(SplashState.UpdateChecking)
    val splashState: LiveData<SplashState> get() = _splashState


    fun onClickTryAgain() {

    }

}