package com.techlads.composetv.navigation

sealed class Screens(val title: String) {
    object Login : Screens("login")
    object LoginToken : Screens("login_token")
    object Home : Screens("home_screen")
}