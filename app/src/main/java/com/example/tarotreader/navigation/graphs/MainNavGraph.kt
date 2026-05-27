package com.example.tarotreader.navigation.graphs


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tarotreader.navigation.Graph
import com.example.tarotreader.navigation.Routes
import com.example.tarotreader.ui.screens.*

fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {

    navigation(
        route = Graph.MAIN,
        startDestination = Routes.Home.route
    ) {

        composable(Routes.Home.route) {

            HomeScreen(
//                onProfileClick = {
//                    navController.navigate(Routes.Profile.route)
//                },
//
//                onHistoryClick = {
//                    navController.navigate(Routes.History.route)
//                },
//
//                onPaywallClick = {
//                    navController.navigate(Graph.PAYWALL)
//                }
            )
        }

//        composable(Routes.Profile.route) {
//            ProfileScreen()
//        }
//
//        composable(Routes.History.route) {
//            HistoryScreen()
//        }
    }
}