package com.techlads.composetv.settings

import com.techlads.composetv.settings.data.SettingsMenuModel
import com.techlads.composetv.settings.navigation.SettingsScreens


object SettingsMenuData {
    val menu by lazy {
        listOf(
            SettingsMenuModel("Profile", SettingsScreens.Profile.title),
            SettingsMenuModel("About Me", SettingsScreens.AboutMe.title)
        )
    }
}