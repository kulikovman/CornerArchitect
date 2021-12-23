package com.searcharchitect.two.screen.search.view

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.searcharchitect.common.model.Contact
import com.searcharchitect.common.utility.extension.getAppVersion
import com.searcharchitect.two.R
import com.searcharchitect.two.screen.search.SearchState
import com.searcharchitect.two.ui.common.CommonOutlinedTextField
import com.searcharchitect.two.ui.theme.SearchArchitectTheme

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SearchDefaultPreview() {
    SearchArchitectTheme {
        SearchDefault(
            viewModelState = MutableLiveData(SearchState.StartSearch),
            updateContactList = { l, s, n -> },
            appVersion = "2.1.5",
            dataVersion = "3",
            openDetailScreen = {}
        )
    }
}


@Composable
fun SearchDefault(
    viewModelState: LiveData<SearchState>,
    updateContactList: (location: String, specialization: String, name: String) -> Unit,
    appVersion: String,
    dataVersion: String,
    openDetailScreen: (contact: Contact) -> Unit
) {
    val viewState = viewModelState.observeAsState()
    val isShowInfoDialog = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.search_architect),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(top = 15.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Info icon",
                tint = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .clickable { isShowInfoDialog.value = true }
                    .padding(top = 20.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            var location by remember { mutableStateOf("") }
            var specialization by remember { mutableStateOf("") }
            var name by remember { mutableStateOf("") }

            CommonOutlinedTextField(
                value = location,
                onValueChange = {
                    location = it
                    updateContactList(location, specialization, name)
                },
                label = stringResource(R.string.location),
                leadingIcon = Icons.Filled.LocationCity,
                trailingIcon = Icons.Outlined.Clear,
                onClickTrailingIcon = {
                    location = ""
                    updateContactList(location, specialization, name)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            CommonOutlinedTextField(
                value = specialization,
                onValueChange = {
                    specialization = it
                    updateContactList(location, specialization, name)
                },
                label = stringResource(R.string.specialization),
                leadingIcon = Icons.Filled.Work,
                trailingIcon = Icons.Outlined.Clear,
                onClickTrailingIcon = {
                    specialization = ""
                    updateContactList(location, specialization, name)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            CommonOutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    updateContactList(location, specialization, name)
                },
                label = stringResource(R.string.name),
                leadingIcon = Icons.Filled.Person,
                trailingIcon = Icons.Outlined.Clear,
                onClickTrailingIcon = {
                    name = ""
                    updateContactList(location, specialization, name)
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        when (val state = viewState.value) {
            is SearchState.StartSearch -> StartTypingSearchQuery()
            is SearchState.Loading -> SearchProgress()
            is SearchState.Result -> SearchResult(
                contacts = state.contacts,
                openDetailScreen = { openDetailScreen(it) }
            )
            is SearchState.Empty -> NothingFound()
        }

        if (isShowInfoDialog.value) {
            InfoDialog(
                version = "v $appVersion / $dataVersion",
                onClickEmail = {},
                dismiss = { isShowInfoDialog.value = false }
            )
        }
    }
}