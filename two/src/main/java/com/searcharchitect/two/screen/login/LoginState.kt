package com.searcharchitect.two.screen.login

sealed class LoginState {

    object Default : LoginState()
    object Loading : LoginState()
    object Error : LoginState()

}
