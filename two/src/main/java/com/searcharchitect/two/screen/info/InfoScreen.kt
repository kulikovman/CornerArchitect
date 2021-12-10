package com.searcharchitect.two.screen.info

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.searcharchitect.two.screen.detail.DetailState
import com.searcharchitect.two.screen.detail.view.DetailDefault
import com.searcharchitect.two.screen.info.view.InfoDefault
import com.searcharchitect.two.utility.SampleData

@Composable
fun InfoScreen(
    navController: NavController,
    infoViewModel: InfoViewModel
) {
    val viewState = infoViewModel.state.observeAsState()

    when (viewState.value) {
        InfoState.Default -> InfoDefault(
            version = "v 2.1.5 / 3",
            onClickEmail = {}
        )
    }
}