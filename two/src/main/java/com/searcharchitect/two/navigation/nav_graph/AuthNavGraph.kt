package com.searcharchitect.two.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.searcharchitect.two.navigation.Screen
import com.searcharchitect.two.screen.login.LoginScreen
import com.searcharchitect.two.screen.splash.SplashScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.Splash.route,
        route = AUTH_GRAPH_ROUTE
    ){
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
    }
}