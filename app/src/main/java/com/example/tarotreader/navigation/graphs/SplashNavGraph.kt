package com.example.tarotreader.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tarotreader.navigation.Graph
import com.example.tarotreader.navigation.Routes
import com.example.tarotreader.ui.screens.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    navController: NavController
) {

    navigation(
        route = Graph.SPLASH,
        startDestination = Routes.Splash.route
    ) {

        composable(Routes.Splash.route) {

            SplashScreen(
                onNavigateToHome = {

                    navController.navigate(Graph.MAIN) {

                        popUpTo(Graph.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}