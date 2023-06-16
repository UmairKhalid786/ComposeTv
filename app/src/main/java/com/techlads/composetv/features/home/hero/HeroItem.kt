@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.home.hero

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.tv.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.techlads.composetv.R
import com.techlads.composetv.theme.ComposeTvTheme

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
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f))
                    .padding(32.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Heading",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraLight),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview
@Composable
fun HeroItemPrev() {
    ComposeTvTheme(true) {
        HeroItem()
    }
}