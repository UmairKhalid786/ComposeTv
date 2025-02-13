package com.techlads.composetv.navigation

import com.techlads.composetv.navigation.Screens.ProductDetail.Args.ID

sealed class Screens(val route: String) {
    object Login : Screens("login")
    object LoginToken : Screens("login_token")
    object Home : Screens("home_screen")
    object Player : Screens("player_screen")
    object ProductDetail : Screens("product_detail/{${Args.ID}}") {
        object Args {
            const val ID = "id"
        }
        fun createRoute(id: Int) = route.replace("{$ID}", id.toString(), false)
    }
    object WhoIsWatching : Screens("who_is_watching")
    object Mp3Player : Screens("mp3_player")
}
