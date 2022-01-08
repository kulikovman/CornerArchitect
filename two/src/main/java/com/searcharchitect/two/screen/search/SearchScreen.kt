package com.searcharchitect.two.screen.search


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.searcharchitect.common.utility.extension.getAppVersion
import com.searcharchitect.common.utility.extension.sendEmail
import com.searcharchitect.two.R
import com.searcharchitect.two.navigation.Screen
import com.searcharchitect.two.screen.search.view.SearchDefault

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val psthvEmail = stringResource(R.string.psthv_email)

    SearchDefault(
        viewModelState = viewModel.state,
        locationField = viewModel.location,
        specializationField = viewModel.specialization,
        nameField = viewModel.name,
        updateContactList = viewModel::updateContactList,
        appVersion = context.getAppVersion(true).orEmpty(),
        dataVersion = viewModel.dataVersion,
        onClickEmail = { context.sendEmail(psthvEmail) },
        openDetailScreen = { contact ->
            viewModel.saveContact(contact)
            navController.navigate(Screen.Detail.route)
        }
    )
}