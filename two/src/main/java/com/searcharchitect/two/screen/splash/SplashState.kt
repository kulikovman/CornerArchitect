package com.searcharchitect.two.screen.splash

sealed class SplashState {

    object UpdateChecking : SplashState()
    object DataLoading : SplashState()
    object Preparation : SplashState()
    object Error : SplashState()

    object OpenLoginScreen : SplashState()
    object OpenSearchScreen : SplashState()

}
