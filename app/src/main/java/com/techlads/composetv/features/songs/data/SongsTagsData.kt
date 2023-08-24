package com.techlads.composetv.features.songs.data

import androidx.compose.ui.graphics.Color
import java.util.Random

object SongsTagsData {
    fun generateRandomColor(): Color {
        val rnd = Random()
        return Color(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}
