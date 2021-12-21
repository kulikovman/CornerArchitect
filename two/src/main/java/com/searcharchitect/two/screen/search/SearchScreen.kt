package com.searcharchitect.two.screen.search


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.searcharchitect.two.navigation.Screen
import com.searcharchitect.two.screen.search.view.SearchDefault

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {
    SearchDefault(
        viewModelState = searchViewModel.state,
        updateContactList = searchViewModel::updateContactList,
        openDetailScreen = { contact ->
            searchViewModel.saveContact(contact)
            navController.navigate(Screen.Detail.route)
        }
    )
}