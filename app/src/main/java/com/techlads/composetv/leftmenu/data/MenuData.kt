package com.techlads.composetv.leftmenu.data

import com.techlads.composetv.home.navigation.NestedScreens.*
import com.techlads.composetv.leftmenu.model.MenuItem
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.*

object MenuData {
    val menuItems = listOf(
        MenuItem(Home.title, "Home", LineAwesomeIcons.HomeSolid),
        MenuItem(Search.title, "Search", LineAwesomeIcons.SearchSolid),
        MenuItem(Movies.title, "Movies", LineAwesomeIcons.VideoSolid),
        MenuItem(Songs.title, "Songs", LineAwesomeIcons.MusicSolid),
        MenuItem(Favorites.title, "Favorites", LineAwesomeIcons.HeartSolid),
    )
}