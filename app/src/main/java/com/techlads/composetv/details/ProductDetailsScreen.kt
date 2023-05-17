package com.techlads.composetv.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.R
import com.techlads.composetv.widgets.BorderedFocusableItem

@Composable
fun ProductDetailsScreen() {
    ProductDetailsContent()
}

@Composable
fun ProductDetailsContent() {
    Box {
        SearchIcon(modifier = Modifier
            .size(80.dp)
            .align(Alignment.TopStart)
            .padding(24.dp)
            .zIndex(1f)
        )

        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxSize()
        ) {
            BannerImage(modifier = Modifier
                .fillMaxSize()
                .weight(.4f)
            )
            Column(modifier = Modifier.weight(.6f)) {
                ButtonSection()
                DetailsSection()
            }
        }

        ThumbnailImageCard(modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(start = 30.dp),
            parent = 1, child = 1)
    }
}


@Composable
fun SearchIcon(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "search",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun BannerImage(modifier: Modifier) {
    Image(
        modifier = modifier
            .fillMaxSize()
            .height(200.dp),
        painter = painterResource(id = R.drawable.hero_item),
        contentDescription = "Hero item background",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ButtonSection() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color(0xFF2D2D44)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(280.dp))
        Button(
            modifier = Modifier,
            shape = RoundedCornerShape(4.dp),
            contentPadding = PaddingValues(vertical = 0.dp, horizontal = 16.dp),
            onClick = {  }) {
            Text(modifier = Modifier.padding(horizontal = 16.dp),
                text = "Play")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            text = "Watch Trailer")
    }
}

@Composable
fun DetailsSection() {
    Row(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.size(220.dp))
        Column(
            Modifier
                .fillMaxWidth()
                //.weight(.7f)
                .padding(24.dp),
            horizontalAlignment = Alignment.Start) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(modifier = Modifier,
                color = Color.White,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineLarge,
                text = "Batman")
            Spacer(modifier = Modifier.size(10.dp))
            MovieInfoSection()
            Spacer(modifier = Modifier.size(10.dp))
            Text(modifier = Modifier,
                color = Color.White,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge,
                text = "Batman ventures into Gotham City's underworld when a sadistic killer leaves behind a trail of cryptic clues. ")
        }
    }
}

@Composable
fun MovieInfoSection() {
    Row(verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center) {
        Text(modifier = Modifier,
            color = Color.White,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            text = "2022")
        Spacer(modifier = Modifier.size(12.dp))
        Text(modifier = Modifier,
            color = Color.White,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            text = "2h 56mm")
        Spacer(modifier = Modifier.size(12.dp))
        Rating(rating = "8.3")
    }
}

@Composable
fun Rating(rating: String) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = "search",
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(modifier = Modifier,
            color = Color.White,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            text = rating)
    }
}

@Composable
fun ThumbnailImageCard(modifier: Modifier, parent: Int, child: Int) {
    BorderedFocusableItem(
        onClick = {  },
        modifier = modifier
            .padding(8.dp)
            .size(width = 150.dp, height = 200.dp)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Item $parent x $child", textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun PreviewDetailsScreen() {
    ProductDetailsScreen()
}
