package com.techlads.composetv.navigation

sealed class Screens(val title: String) {
    object Login : Screens("login")
    object LoginToken : Screens("login_token")
    object Home : Screens("home_screen")
    object Player : Screens("player_screen")
    object ProductDetail : Screens("product_detail")
    object WhoIsWatching : Screens("who_is_watching")
    object Mp3Player : Screens("mp3_player")
}
