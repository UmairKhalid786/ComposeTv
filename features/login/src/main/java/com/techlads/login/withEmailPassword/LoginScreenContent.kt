package com.techlads.login.withEmailPassword

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
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
import com.techlads.uicomponents.widgets.TvButton
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun BoxScope.LoginPageContent(
    onLoginClick: (user: String, psw: String) -> Unit,
) {
    val insets = WindowInsets.ime.union(WindowInsets.systemBars)
    val brush = Brush.radialGradient(
        listOf(
            MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
            MaterialTheme.colorScheme.background.copy(0.1f)
        )
    )

    Column(
        modifier = Modifier
            .windowInsetsPadding(insets)
            .verticalScroll(rememberScrollState())
            .imePadding()
            .background(brush)
            .align(Alignment.Center)
            .padding(horizontal = 60.dp)
            .fillMaxHeight()
            .fillMaxWidth(0.4f),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        ScreenHeading("LOGIN")
        TvTextField(
            value = username.value, placeholder = "Username",
            modifier = Modifier.fillMaxWidth(),
        ) {
            username.value = it
        }
        TvTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password.value,
            placeholder = "Password",
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
        ) { password.value = it }

        Spacer(modifier = Modifier.height(20.dp))

        TvButton(
            modifier = Modifier
                .requestFocusWhenVisibleInWindow()
                .padding(start = 20.dp, end = 20.dp),
            onClick = { onLoginClick(username.value, password.value) },
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Login",
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
fun CrossFadeBackground(
    state: CrossFadeState, modifier: Modifier = Modifier
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val animatedOffset by animateFloatAsState(
        targetValue = offsetX,
        label = "",
        animationSpec = tween(durationMillis = 1000 * 20, easing = LinearEasing)
    )

    val backgroundState = backgroundImageState()
    val targetBitmap by remember(backgroundState) { backgroundState.drawable }

    var imageIndex = remember { 0 }

    LaunchedEffect(Unit) {
        while (true) {
            imageIndex = (imageIndex + 1) % state.images.size
            backgroundState.load(state.images[imageIndex], onError = {
                offsetX = if (offsetX <= 0) state.durationMs else state.durationMs * -1
            }, onSuccess = {
                offsetX = if (offsetX <= 0) state.durationMs else state.durationMs * -1
            })
            delay(state.delaySec.seconds)
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
    MaterialTheme {
        Box {
            LoginPageContent(onLoginClick = { _, _ -> })
        }
    }
}

@Composable
fun Modifier.requestFocusWhenVisibleInWindow(): Modifier {
    val focusRequester = remember { FocusRequester() }
    var isVisible by remember { mutableStateOf(false) }

    // You can simplify this part depending on your needs.
    val config = LocalConfiguration.current
    val density = LocalDensity.current

    val windowBounds = remember(config) {
        with(density) {
            Rect(
                0f,
                0f,
                config.screenWidthDp.dp.toPx(),
                config.screenHeightDp.dp.toPx()
            )
        }
    }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            focusRequester.requestFocus()
        }
    }

    return this
        .focusRequester(focusRequester)
        .onGloballyPositioned { coords ->
            if (coords.isAttached) {
                val itemBounds = coords.boundsInWindow()
                isVisible = itemBounds.overlaps(windowBounds)
            }
        }
}