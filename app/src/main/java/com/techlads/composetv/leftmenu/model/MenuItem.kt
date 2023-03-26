package com.techlads.composetv.leftmenu.model

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: String,
    val text: String,
    val icon: ImageVector? = null
)