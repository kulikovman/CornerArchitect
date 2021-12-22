package com.searcharchitect.two.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.searcharchitect.two.navigation.nav_graph.SetupNavGraph
import com.searcharchitect.two.ui.theme.SearchArchitectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchArchitectTheme {
                SetupNavGraph(rememberNavController())
            }
        }
    }
}