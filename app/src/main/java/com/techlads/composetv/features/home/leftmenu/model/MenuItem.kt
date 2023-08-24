package com.techlads.composetv.features.home.leftmenu.model

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: String,
    val text: String,
    val icon: ImageVector? = null,
)
