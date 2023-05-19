package com.techlads.composetv.features.home.leftmenu

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.tv.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.TvSolid

@Composable
fun MenuHeader(expanded: Boolean = true) {
    val animatedAlpha = animateFloatAsState(
        animationSpec = keyframes {
            this.delayMillis = 500
            this.durationMillis = 500
        },
        targetValue = if (expanded) 1f else 0f,
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = LineAwesomeIcons.TvSolid,
            contentDescription = "App icon",
            tint = LocalContentColor.current
        )
        Text(
            text = "Compose Tv",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.alpha(animatedAlpha.value),
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun MenuHeaderPrev() {
    MenuHeader(true)
}