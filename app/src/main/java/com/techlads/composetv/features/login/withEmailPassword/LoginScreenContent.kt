package com.techlads.composetv.features.login.withEmailPassword

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.theme.ComposeTvTheme
import com.techlads.composetv.utils.Storage.movies
import com.techlads.composetv.widgets.TvButton
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun BoxScope.LoginPageContent(
    onLoginClick: (user: String, psw: String) -> Unit,
) {
    val background = MaterialTheme.colorScheme.surface

    LoginBackground(modifier = Modifier
        .fillMaxSize()
        .drawWithContent {
            drawContent()
            drawRect(
                Brush.radialGradient(
                    listOf(
                        background.copy(0.8f),
                        background.copy(0.7f),
                        background.copy(0.6f),
                    )
                ), size = size
            )
        })

    Column(
        modifier = Modifier
            .align(Alignment.Center)
            .padding(horizontal = 60.dp)
            .fillMaxWidth(0.4f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        ScreenHeading("LOGIN")
        Spacer(modifier = Modifier.height(20.dp))
        TvTextField(
            value = username.value, placeholder = "Username",
            modifier = Modifier.fillMaxWidth(),
        ) {
            username.value = it
        }
        Spacer(modifier = Modifier.height(20.dp))
        TvTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password.value,
            placeholder = "Password",
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
        ) { password.value = it }
        Spacer(modifier = Modifier.height(40.dp))

        TvButton(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            onClick = { onLoginClick(username.value, password.value) },
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "LOGIN",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                ),
            )
        }
    }
}


@Composable
fun LoginBackground(modifier: Modifier = Modifier) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val animatedOffset by animateFloatAsState(
        offsetX, label = "", animationSpec = tween(1000 * 20, easing = LinearEasing)
    )

    val backgroundState = backgroundImageState()
    val targetBitmap by remember(backgroundState) { backgroundState.drawable }

    var imageIndex = remember { 0 }

    LaunchedEffect(Unit) {
        while (true) {
            imageIndex = (imageIndex + 1) % movies.size
            backgroundState.load(movies[imageIndex].imageUrl, onError = {
                offsetX = if (offsetX <= 0) 300f else -300f
            }, onSuccess = {
                offsetX = if (offsetX <= 0) 300f else -300f
            })
            delay(10.seconds)
        }
    }

    Crossfade(
        targetBitmap, modifier = modifier.fillMaxSize(), label = "",
    ) { image ->
        image?.let {
            Image(
                bitmap = it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .graphicsLayer(
                        translationX = animatedOffset, translationY = animatedOffset / 2
                    )
                    .fillMaxSize()
                    .scale(1.5f)
            )
        }
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun LoginPagePrev() {
    ComposeTvTheme {
        Box {
            LoginPageContent { u, p ->
            }
        }
    }
}
