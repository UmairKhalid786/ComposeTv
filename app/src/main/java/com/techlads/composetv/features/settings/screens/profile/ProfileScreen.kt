package com.techlads.composetv.features.settings.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.auth.AuthState
import com.techlads.auth.data.User
import com.techlads.composetv.R
import com.techlads.composetv.features.settings.SettingsMenuViewModel
import com.techlads.composetv.features.settings.data.SettingsMenuModel
import com.techlads.composetv.features.settings.screens.PreferencesContainer
import com.techlads.uicomponents.widgets.TvButton
import kotlin.time.ExperimentalTime

@Composable
fun ProfileScreen(
    viewModel: SettingsMenuViewModel = hiltViewModel(),
) {
    val authState by viewModel.authState.collectAsStateWithLifecycle()
    val isLoggingOut by viewModel.isLoggingOut.collectAsStateWithLifecycle()

    PreferencesContainer(preference = SettingsMenuModel("Profile", "profile")) {
        ProfilesContent(
            authState = authState,
            isLoggingOut = isLoggingOut,
            onLogout = viewModel::logout,
        )
    }
}

@Composable
fun ProfilesContent(
    authState: AuthState,
    isLoggingOut: Boolean,
    onLogout: () -> Unit,
) {
    val loggedInState = authState as? AuthState.LoggedIn
    val username = loggedInState?.user?.name ?: loggedInState?.user?.id ?: "No user"
    val email = loggedInState?.user?.email ?: "Not provided by TMDB session login"
    val status = when (authState) {
        AuthState.Loading -> "Checking session"
        is AuthState.LoggedIn -> "Logged in"
        AuthState.LoggedOut -> "Logged out"
    }
    val sessionState = if (loggedInState?.sessionId.isNullOrBlank()) "No active session" else "TMDB session is active"

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProfilePicture()
            Spacer(modifier = Modifier.size(20.dp))
            UserDetails(
                status = status,
                username = username,
                email = email,
                sessionState = sessionState,
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        Row {
            TvButton(
                onClick = onLogout,
                enabled = loggedInState != null && !isLoggingOut,
                modifier = Modifier.width(180.dp),
            ) {
                Text(
                    text = if (isLoggingOut) "Logging Out..." else "Logout",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
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
        contentDescription = "User profile",
    )
}

@Composable
fun UserDetails(
    status: String,
    username: String,
    email: String,
    sessionState: String,
) {
    Column {
        Text(text = username, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Status: $status",
            style = MaterialTheme.typography.labelSmall,
            color = LocalContentColor.current.copy(alpha = 0.4f),
        )
        Text(
            text = "Email: $email",
            style = MaterialTheme.typography.labelSmall,
            color = LocalContentColor.current.copy(alpha = 0.4f),
        )
        Text(
            text = sessionState,
            style = MaterialTheme.typography.labelSmall,
            color = LocalContentColor.current.copy(alpha = 0.4f),
        )
    }
}

@OptIn(ExperimentalTime::class)
@androidx.compose.ui.tooling.preview.Preview
@Composable
fun ProfileScreenPrev() {
    ProfilesContent(
        authState = AuthState.LoggedIn(
            user = User(
                id = "tmdb-user",
                name = "TMDB User",
                email = null,
            ),
            sessionId = "session-id",
        ),
        isLoggingOut = false,
        onLogout = {},
    )
}
