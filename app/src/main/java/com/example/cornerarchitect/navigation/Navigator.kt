package com.example.cornerarchitect.navigation

import androidx.navigation.NavController
import com.example.cornerarchitect.R
import javax.inject.Inject

interface INavigator {

    fun setNavController(navController: NavController)

    fun actionSplashToCity()

}

class Navigator @Inject constructor(

) : INavigator {

    private var navController: NavController? = null


    override fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun actionSplashToCity() {
        navController?.navigate(R.id.action_splashFragment_to_cityFragment)
    }

}