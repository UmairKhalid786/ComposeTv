@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.composetv.features.settings.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.R
import com.techlads.composetv.features.settings.data.SettingsMenuModel
import com.techlads.composetv.features.settings.screens.PreferencesContainer
import com.techlads.composetv.widgets.TvButton

@Composable
fun ProfileScreen() {
    PreferencesContainer(preference = SettingsMenuModel("Profile", "profile")) {
        ProfilesContent()
    }
}

@Composable
fun ProfilesContent() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProfilePicture()
            Spacer(modifier = Modifier.size(20.dp))
            UserDetails()
        }
        Spacer(modifier = Modifier.size(5.dp))
        Row {
            Spacer(modifier = Modifier.size(120.dp))
            TvButton(onClick = {}, modifier = Modifier.width(120.dp)) {
                Text(
                    text = "Save",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            TvButton(onClick = {}, modifier = Modifier.width(120.dp)) {
                Text(
                    text = "Cancel",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ProfilePicture() {
    Image(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .shadow(elevation = 12.dp, shape = CircleShape, clip = true)
            .border(2.dp, LocalContentColor.current, CircleShape),
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "User profile"
    )
}

@Composable
fun UserDetails() {
    Column {
        Text(text = "Umair Khalid", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Android Developer", style = MaterialTheme.typography.labelSmall,
            color = LocalContentColor.current.copy(alpha = 0.4f)
        )
        Text(
            text = "Github: https://github.com/UmairKhalid786",
            style = MaterialTheme.typography.labelSmall,
            color = LocalContentColor.current.copy(alpha = 0.4f)
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun ProfileScreenPrev() {
    ProfileScreen()
}