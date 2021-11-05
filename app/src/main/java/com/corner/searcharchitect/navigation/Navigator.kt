package com.corner.searcharchitect.navigation

import androidx.navigation.NavController
import com.corner.searcharchitect.R
import javax.inject.Inject

interface INavigator {

    fun setNavController(navController: NavController)

    fun goBack()

    fun actionSplashToSearch()
    fun actionSearchToDetail()
    fun actionSearchToIndoDialog()

}

class Navigator @Inject constructor(

) : INavigator {

    private var navController: NavController? = null


    override fun setNavController(navController: NavController) {
        this.navController = navController
    }


    override fun goBack() {
        navController?.navigateUp()
    }

    override fun actionSplashToSearch() {
        navController?.navigate(R.id.action_splashFragment_to_searchFragment)
    }

    override fun actionSearchToDetail() {
        navController?.navigate(R.id.action_searchFragment_to_detailFragment)
    }

    override fun actionSearchToIndoDialog() {
        navController?.navigate(R.id.action_searchFragment_to_infoDialogFragment)
    }

}