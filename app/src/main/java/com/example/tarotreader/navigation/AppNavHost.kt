package com.example.tarotreader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.tarotreader.navigation.graphs.mainNavGraph
import com.example.tarotreader.navigation.graphs.splashNavGraph

@Composable
fun AppNavHost(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH
    ) {

        splashNavGraph(navController)

        mainNavGraph(navController)

       // paywallNavGraph(navController)
    }
}