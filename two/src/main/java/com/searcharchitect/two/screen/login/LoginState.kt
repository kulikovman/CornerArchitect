package com.searcharchitect.two.screen.login

sealed class LoginState {

    object Default : LoginState()
    object Checking : LoginState()
    object Error : LoginState()

    object OpenSearchScreen : LoginState()

}
