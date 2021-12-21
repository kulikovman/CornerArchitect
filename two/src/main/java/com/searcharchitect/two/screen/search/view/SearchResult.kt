package com.searcharchitect.two.screen.search.view

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.searcharchitect.common.model.Contact
import com.searcharchitect.two.R
import com.searcharchitect.two.ui.theme.SearchArchitectTheme
import com.searcharchitect.two.ui.theme.Teal400

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SearchResultPreview() {
    SearchArchitectTheme {
        SearchResult(
            contacts = emptyList(),
            openDetailScreen = {}
        )
    }
}


@Composable
fun SearchResult(
    contacts: List<Contact>,
    openDetailScreen: (contact: Contact) -> Unit
) {
    LazyColumn {
        items(contacts) { contact ->
            ItemSearchContact(
                contact = contact,
                openDetailScreen = { openDetailScreen(it) }
            )
        }
    }
}