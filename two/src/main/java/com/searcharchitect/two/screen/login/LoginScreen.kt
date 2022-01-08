package com.searcharchitect.two.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.searcharchitect.common.utility.extension.sendEmail
import com.searcharchitect.two.R
import com.searcharchitect.two.navigation.Screen
import com.searcharchitect.two.screen.login.view.LoginChecking
import com.searcharchitect.two.screen.login.view.LoginDefault
import com.searcharchitect.two.screen.login.view.LoginError

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val viewState = loginViewModel.state.observeAsState()

    val context = LocalContext.current
    val psthvEmail = stringResource(R.string.psthv_email)

    when (viewState.value) {
        LoginState.Default -> LoginDefault(
            onClickSignIn = loginViewModel::onClickSignIn,
            onClickEmail = { context.sendEmail(psthvEmail) },
        )
        LoginState.Checking -> LoginChecking()
        LoginState.Error -> LoginError(
            onClickSignIn = loginViewModel::onClickSignIn,
            onClickEmail = { context.sendEmail(psthvEmail) },
        )
        LoginState.OpenSearchScreen -> {
            navController.navigate(Screen.Search.route) {
                launchSingleTop = true
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }
}