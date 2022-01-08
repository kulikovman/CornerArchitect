package com.searcharchitect.two.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.searcharchitect.common.utility.extension.*
import com.searcharchitect.two.screen.detail.view.DetailDefault

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