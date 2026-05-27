package com.example.tarotreader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun App() {

    val navController = rememberNavController()

    AppNavHost(navController = navController)
}