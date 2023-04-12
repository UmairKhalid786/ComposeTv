package com.techlads.composetv.settings.screens.profile

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
import androidx.compose.ui.unit.dp
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.R
import com.techlads.composetv.settings.data.SettingsMenuModel
import com.techlads.composetv.settings.screens.PreferencesContainer
import com.techlads.composetv.widgets.Button

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
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .shadow(elevation = 12.dp, shape = CircleShape, clip = true)
                    .border(2.dp, LocalContentColor.current, CircleShape),
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "User profile"
            )
            Spacer(modifier = Modifier.size(20.dp))
            UserDetails()
        }
        Spacer(modifier = Modifier.size(5.dp))
        Row {
            Spacer(modifier = Modifier.size(120.dp))
            Button(text = "Save") {

            }
            Spacer(modifier = Modifier.size(8.dp))
            Button(text = "Cancel") {

            }
        }
    }
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