package com.example.tarotreader.navigation


sealed class Routes(val route: String) {

    // Splash
    object Splash : Routes("splash")

    // Main
    object Home : Routes("home")
    object Profile : Routes("profile")
    object History : Routes("history")

    // Paywall
    object Paywall : Routes("paywall")
}