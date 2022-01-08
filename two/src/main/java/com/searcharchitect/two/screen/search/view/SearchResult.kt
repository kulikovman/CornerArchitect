package com.searcharchitect.two.screen.search.view

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.searcharchitect.common.model.Contact
import com.searcharchitect.two.ui.theme.SearchArchitectTheme

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
    LazyColumn() {
        items(contacts) { contact ->
            ItemSearchContact(
                contact = contact,
                openDetailScreen = { openDetailScreen(it) }
            )
        }
    }
}