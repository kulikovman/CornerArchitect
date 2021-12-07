package com.searcharchitect.two.screen.splash

sealed class SplashState {

    object UpdateChecking : SplashState()
    object DataLoading : SplashState()
    object Preparation : SplashState()
    object DataLoaded : SplashState()
    object Error : SplashState()

}
