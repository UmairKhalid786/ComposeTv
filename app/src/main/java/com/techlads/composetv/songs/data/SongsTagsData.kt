package com.techlads.composetv.songs.data

import androidx.compose.ui.graphics.Color
import java.util.*

object SongsTagsData {
    fun generateRandomColor() : Color {
        val rnd = Random()
        return Color(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}