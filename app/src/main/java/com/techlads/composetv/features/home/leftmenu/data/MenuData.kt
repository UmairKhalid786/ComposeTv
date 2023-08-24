package com.techlads.composetv.features.home.leftmenu.data

import com.techlads.composetv.features.home.leftmenu.model.MenuItem
import com.techlads.composetv.features.home.navigation.NestedScreens.Favorites
import com.techlads.composetv.features.home.navigation.NestedScreens.Home
import com.techlads.composetv.features.home.navigation.NestedScreens.Movies
import com.techlads.composetv.features.home.navigation.NestedScreens.Search
import com.techlads.composetv.features.home.navigation.NestedScreens.Settings
import com.techlads.composetv.features.home.navigation.NestedScreens.Songs
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.CogSolid
import compose.icons.lineawesomeicons.HeartSolid
import compose.icons.lineawesomeicons.HomeSolid
import compose.icons.lineawesomeicons.MusicSolid
import compose.icons.lineawesomeicons.SearchSolid
import compose.icons.lineawesomeicons.VideoSolid

object MenuData {
    val menuItems = listOf(
        MenuItem(Home.title, "Home", LineAwesomeIcons.HomeSolid),
        MenuItem(Search.title, "Search", LineAwesomeIcons.SearchSolid),
        MenuItem(Movies.title, "Movies", LineAwesomeIcons.VideoSolid),
        MenuItem(Songs.title, "Songs", LineAwesomeIcons.MusicSolid),
        MenuItem(Favorites.title, "Favorites", LineAwesomeIcons.HeartSolid),
    )

    val settingsItem = MenuItem(
        Settings.title,
        "Settings",
        LineAwesomeIcons.CogSolid,
    )
}
