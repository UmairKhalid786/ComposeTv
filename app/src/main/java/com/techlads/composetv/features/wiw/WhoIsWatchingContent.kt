package com.techlads.composetv.features.wiw

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.techlads.composetv.R
import com.techlads.composetv.features.wiw.data.Avatar
import kotlinx.coroutines.delay

private val avatarList = listOf(
    Avatar(
        title = "Jack",
        image = R.drawable.boy,
    ),
    Avatar(
        title = "Alice",
        image = R.drawable.girl,
    ),
    Avatar(
        title = "Archit",
        image = R.drawable.old,
    ),
)

@Composable
fun WhoIsWatchingContent(onProfileSelection: (avatar: Avatar) -> Unit) {
    // initial height set at 0.dp
    var containerWidth by remember { mutableStateOf(0.dp) }
    // get local density from composable
    val density = LocalDensity.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned {
                containerWidth = with(density) {
                    it.size.width.toDp()
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            var selectedAvatar by remember {
                mutableStateOf("")
            }

            var isLeft by remember {
                mutableStateOf(false)
            }

            var lastPosition by remember {
                mutableStateOf(0)
            }

            val requester = remember { FocusRequester() }

            LaunchedEffect(Unit) {
                delay(10)
                requester.requestFocus()
            }

            Text(
                text = "Who's Watching?",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 38.sp),
                modifier = Modifier.padding(top = 32.dp),
            )

            Spacer(modifier = Modifier.size(68.dp))

            LazyRow(
                contentPadding = PaddingValues(vertical = 32.dp),
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(avatarList.size) {
                    val item = avatarList[it]
                    val itemIndex = it
                    ScaleAbleAvatar(
                        avatarRes = item.image,
                        modifier = Modifier
                            .then(if (it == 1) Modifier.focusRequester(requester) else Modifier)
                            .onFocusChanged {
                                if (it.isFocused) {
                                    isLeft = lastPosition > itemIndex
                                    lastPosition = itemIndex
                                }
                                selectedAvatar = item.title
                            },
                        onProfileSelection = {
                            onProfileSelection(item)
                        }
                    )
                }
            }

            if (selectedAvatar.isNotEmpty()) {
                Spacer(modifier = Modifier.padding(top = 48.dp))
                ProfileName(name = selectedAvatar, isLeft)
            }

            Spacer(modifier = Modifier.size(38.dp))

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Settings",
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProfileName(name: String, scaleUp: Boolean) {
    AnimatedContent(
        targetState = name,
        transitionSpec = {
            // Compare the incoming number with the previous number.
            if (scaleUp) {
                // If the target number is larger, it slides up and fades in
                // while the initial (smaller) number slides up and fades out.
                slideInVertically { height -> height } + fadeIn() with
                    slideOutVertically { height -> -height } + fadeOut()
            } else {
                // If the target number is smaller, it slides down and fades in
                // while the initial number slides down and fades out.
                slideInVertically { height -> -height } + fadeIn() with
                    slideOutVertically { height -> height } + fadeOut()
            }.using(
                // Disable clipping since the faded slide-in/out should
                // be displayed out of bounds.
                SizeTransform(clip = false),
            )
        },
        label = "",
    ) { text ->
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall.copy(fontSize = 24.sp),
            modifier = Modifier.padding(top = 32.dp),
        )
    }
}

@Composable
fun ScaleAbleAvatar(
    modifier: Modifier,
    avatarRes: Int,
    onProfileSelection: () -> Unit
) {
    Surface(
        onClick = {
            onProfileSelection()
        },
        modifier = modifier.padding(horizontal = 32.dp),
        border = ClickableSurfaceDefaults.border(
            border = Border(
                border = BorderStroke(
                    4.dp,
                    Color.Transparent,
                ),
                shape = CircleShape,
            ),
            focusedBorder = Border(
                border = BorderStroke(
                    4.dp,
                    Color.White,
                ),
                shape = CircleShape,
            ),
        ),
        shape = ClickableSurfaceDefaults.shape(shape = CircleShape),
        scale = ClickableSurfaceDefaults.scale(focusedScale = 1.5f),
    ) {
        AvatarIcon(avatarRes = avatarRes, modifier = Modifier.size(120.dp))
    }
}

@Composable
fun AvatarIcon(modifier: Modifier, @DrawableRes avatarRes: Int, description: String? = null) {
    Image(
        painter = painterResource(id = avatarRes),
        contentDescription = description,
        contentScale = ContentScale.Crop,
        modifier = modifier.aspectRatio(1f),
    )
}

@Preview(device = Devices.TV_1080p, showBackground = true)
@Composable
private fun WhoIsWatchingPreview() {
    WhoIsWatchingContent {
    }
}
