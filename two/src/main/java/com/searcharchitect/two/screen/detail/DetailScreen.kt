package com.searcharchitect.two.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.searcharchitect.common.utility.extension.*
import com.searcharchitect.two.screen.detail.view.DetailDefault
import com.searcharchitect.two.screen.splash.SplashState
import com.searcharchitect.two.screen.splash.view.SplashDataLoading
import com.searcharchitect.two.screen.splash.view.SplashError
import com.searcharchitect.two.screen.splash.view.SplashPreparation
import com.searcharchitect.two.screen.splash.view.SplashUpdateChecking
import com.searcharchitect.two.utility.SampleData

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val viewState = detailViewModel.state.observeAsState()

    val context = LocalContext.current

    when (val state = viewState.value) {
        is DetailState.Default -> DetailDefault(
            contact = state.contact!!,
            onClickPhone = { phone -> context.callPhoneNumber(phone) },
            onClickEmail = { email -> context.sendEmail(email) },
            onClickLink = { link -> context.openLink(link) },
            onClickTelegram = { profileId -> context.openTelegram(profileId) },
            onClickInstagram = { profileId -> context.openInstagram(profileId) },
            onClickFacebook = { profileId -> context.openFacebook(profileId) },
            onClickVk = { profileId -> context.openVk(profileId) },
        )
    }
}