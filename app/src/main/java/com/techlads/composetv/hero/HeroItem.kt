package com.techlads.composetv.hero

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techlads.composetv.R

@Composable
fun HeroItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .fillMaxWidth()
            .height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Box {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.hero_item),
                contentDescription = "Hero item background",
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.7f))
                    .padding(32.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Heading", style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraLight)
                )
            }
        }


    }
}

@Preview
@Composable
fun HeroItemPrev() {
    HeroItem()
}