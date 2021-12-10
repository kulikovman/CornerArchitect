package com.searcharchitect.two.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.searcharchitect.two.screen.splash.SplashScreen
import com.searcharchitect.two.screen.splash.SplashViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.searcharchitect.two.screen.detail.DetailScreen
import com.searcharchitect.two.screen.detail.DetailViewModel
import com.searcharchitect.two.screen.info.InfoScreen
import com.searcharchitect.two.screen.info.InfoViewModel
import com.searcharchitect.two.screen.login.LoginScreen
import com.searcharchitect.two.screen.login.LoginViewModel
import com.searcharchitect.two.screen.search.SearchScreen
import com.searcharchitect.two.screen.search.SearchViewModel

const val ROOT_GRAPH = "root_graph"

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
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

        composable(
            route = Screen.Search.route
        ) {
            val searchViewModel= hiltViewModel<SearchViewModel>()
            SearchScreen(
                navController = navController,
                searchViewModel = searchViewModel
            )
        }

        composable(
            route = Screen.Detail.route
        ) {
            val detailViewModel = hiltViewModel<DetailViewModel>()
            DetailScreen(
                navController = navController,
                detailViewModel = detailViewModel
            )
        }

        composable(
            route = Screen.Info.route
        ) {
            val infoViewModel = hiltViewModel<InfoViewModel>()
            InfoScreen(
                navController = navController,
                infoViewModel = infoViewModel
            )
        }
    }
}