package com.techlads.composetv.features.settings

import com.techlads.composetv.features.settings.data.SettingsMenuModel
import com.techlads.composetv.features.settings.navigation.SettingsScreens

object SettingsMenuData {
    val menu by lazy {
        listOf(
            SettingsMenuModel("Profile", SettingsScreens.Profile.title),
            SettingsMenuModel("About Me", SettingsScreens.AboutMe.title),
        )
    }
}
