package com.searcharchitect.two.navigation.nav_graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.searcharchitect.two.navigation.Screen
import com.searcharchitect.two.screen.login.LoginScreen
import com.searcharchitect.two.screen.login.LoginViewModel
import com.searcharchitect.two.screen.splash.SplashScreen
import com.searcharchitect.two.screen.splash.SplashViewModel

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.Splash.route,
        route = AUTH_GRAPH_ROUTE
    ){
        composable(
            route = Screen.Splash.route
        ) {
            val splashViewModel = hiltViewModel<SplashViewModel>()
            SplashScreen(
                navController = navController,
                splashViewModel = splashViewModel
            )
        }

        composable(
            route = Screen.Login.route
        ) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                navController = navController,
                loginViewModel = loginViewModel
            )
        }
    }
}