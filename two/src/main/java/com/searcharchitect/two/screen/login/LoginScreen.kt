package com.searcharchitect.two.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.searcharchitect.two.screen.login.view.LoginDefault
import com.searcharchitect.two.screen.login.view.LoginError
import com.searcharchitect.two.screen.login.view.LoginLoading

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    val viewState = loginViewModel.state.observeAsState()

    when (viewState.value) {
        LoginState.Default -> LoginDefault(
            onClickSignIn = loginViewModel::onClickSignIn,
            onClickEmail = loginViewModel::onClickEmail
        )
        LoginState.Loading -> LoginLoading()
        LoginState.Error -> LoginError(
            onClickSignIn = loginViewModel::onClickSignIn,
            onClickEmail = loginViewModel::onClickEmail
        )
    }
}