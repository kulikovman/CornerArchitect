package com.searcharchitect.two.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.searcharchitect.two.navigation.Screen
import com.searcharchitect.two.screen.login.view.LoginDefault
import com.searcharchitect.two.screen.login.view.LoginError
import com.searcharchitect.two.screen.login.view.LoginChecking

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
        LoginState.Checking -> LoginChecking()
        LoginState.Error -> LoginError(
            onClickSignIn = loginViewModel::onClickSignIn,
            onClickEmail = loginViewModel::onClickEmail
        )
        LoginState.OpenSearchScreen -> {
            navController.navigate(Screen.Search.route) {
                launchSingleTop = true
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }
}