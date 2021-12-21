package com.searcharchitect.two.screen.splash

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.runtime.livedata.observeAsState
import com.searcharchitect.two.navigation.Screen
import com.searcharchitect.two.screen.splash.view.SplashDataLoading
import com.searcharchitect.two.screen.splash.view.SplashError
import com.searcharchitect.two.screen.splash.view.SplashPreparation
import com.searcharchitect.two.screen.splash.view.SplashUpdateChecking

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel
) {
    val viewState = splashViewModel.state.observeAsState()

    when (viewState.value) {
        SplashState.UpdateChecking -> SplashUpdateChecking()
        SplashState.DataLoading -> SplashDataLoading()
        SplashState.Preparation -> SplashPreparation()
        SplashState.Error -> SplashError(
            onClickTryAgain = splashViewModel::onClickTryAgain
        )
        SplashState.OpenLoginScreen -> {
            navController.navigate(Screen.Login.route) {
                launchSingleTop = true
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
        SplashState.OpenSearchScreen -> {
            navController.navigate(Screen.Search.route) {
                launchSingleTop = true
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }
}