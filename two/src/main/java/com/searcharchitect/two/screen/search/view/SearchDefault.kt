package com.searcharchitect.two.screen.search.view

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.searcharchitect.two.R
import com.searcharchitect.two.screen.search.SearchState
import com.searcharchitect.two.ui.theme.SearchArchitectTheme
import com.searcharchitect.two.ui.theme.Teal400

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SearchDefaultPreview() {
    SearchArchitectTheme {
        SearchDefault(
            viewModelState = MutableLiveData(SearchState.StartSearch),
            updateContactList = { l, s, n -> }
        )
    }
}


@Composable
fun SearchDefault(
    viewModelState: LiveData<SearchState>,
    updateContactList: (location: String, specialization: String, name: String) -> Unit
) {
    val viewState = viewModelState.observeAsState()
    val isShowInfoDialog = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            elevation = 4.dp,
            color = Teal400,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                var location by remember { mutableStateOf("") }
                var specialization by remember { mutableStateOf("") }
                var name by remember { mutableStateOf("") }

                TextField(
                    value = location,
                    onValueChange = {
                        location = it
                        updateContactList(location, specialization, name)
                    },
                    label = { Text(stringResource(R.string.location)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = specialization,
                    onValueChange = {
                        specialization = it
                        updateContactList(location, specialization, name)
                    },
                    label = { Text(stringResource(R.string.specialization)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                        updateContactList(location, specialization, name)
                    },
                    label = { Text(stringResource(R.string.name)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Info icon",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable { isShowInfoDialog.value = true }
                        .align(Alignment.End),
                )
            }
        }

        when (val state = viewState.value) {
            is SearchState.StartSearch -> StartTypingSearchQuery()
            is SearchState.Loading -> SearchProgress()
            is SearchState.Result -> SearchResult(state.contacts)
            is SearchState.Empty -> NothingFound()
        }

        if (isShowInfoDialog.value) {
            InfoDialog(
                version = "v 2.1.5 / 3",
                onClickEmail = {},
                dismiss = { isShowInfoDialog.value = false }
            )
        }
    }
}