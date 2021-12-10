package com.searcharchitect.two.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.searcharchitect.two.screen.detail.view.DetailDefault
import com.searcharchitect.two.screen.splash.SplashState
import com.searcharchitect.two.screen.splash.view.SplashDataLoading
import com.searcharchitect.two.screen.splash.view.SplashError
import com.searcharchitect.two.screen.splash.view.SplashPreparation
import com.searcharchitect.two.screen.splash.view.SplashUpdateChecking
import com.searcharchitect.two.utility.SampleData

@Composable
fun DetailScreen(
    navController: NavController,
    detailViewModel: DetailViewModel
) {
    val viewState = detailViewModel.state.observeAsState()

    when (viewState.value) {
        DetailState.Default -> DetailDefault(
            contact = SampleData.testContact,
            onClickBack = navController::popBackStack,
            onClickPhone = {},
            onClickEmail = {},
            onClickLink = {},
            onClickTelegram = {},
            onClickInstagram = {},
            onClickFacebook = {},
            onClickVk = {},
        )
    }
}