package com.example.cornerarchitect.navigation

import androidx.navigation.NavController
import com.example.cornerarchitect.R
import javax.inject.Inject

interface INavigator {

    fun setNavController(navController: NavController)

    fun goBack()

    fun actionSplashToCity()
    fun actionCityToSpecialization()
    fun actionSpecializationToPeople()
    fun actionPeopleToDetail()

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

    override fun actionSplashToCity() {
        navController?.navigate(R.id.action_splashFragment_to_cityFragment)
    }

    override fun actionCityToSpecialization() {
        navController?.navigate(R.id.action_cityFragment_to_specializationFragment)
    }

    override fun actionSpecializationToPeople() {
        navController?.navigate(R.id.action_specializationFragment_to_peopleFragment)
    }

    override fun actionPeopleToDetail() {
        navController?.navigate(R.id.action_peopleFragment_to_detailFragment)
    }

}