package com.techlads.composetv.home

import com.techlads.composetv.leftmenu.model.MenuItem
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.*

object MenuData {
    val menuItems = listOf(
        MenuItem("", "Home", LineAwesomeIcons.HomeSolid),
        MenuItem("", "Search", LineAwesomeIcons.SearchSolid),
        MenuItem("", "Movies", LineAwesomeIcons.VideoSolid),
        MenuItem("", "Songs", LineAwesomeIcons.MusicSolid),
        MenuItem("", "Favorites", LineAwesomeIcons.HeartSolid),
    )
}