package com.techlads.composetv.features.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.features.home.carousel.HomeCarousel
import com.techlads.composetv.features.home.carousel.findIndexById
import com.techlads.composetv.features.home.hero.HeroItem
import com.techlads.composetv.utils.handleDPadKeyEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeNestedScreen(
    homeViewModel: HomeViewModel,
//    navigationBar: (NavigationEvent) -> Unit,
    onItemFocus: (parent: String, child: String) -> Unit,
    onItemClick: (parent: String, child: String) -> Unit,
) {

    val heroItemState by homeViewModel.heroItemState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    val focusState = rememberSaveable {
        mutableStateOf(FocusPosition(0, 0))
    }

    val showCarousel = rememberSaveable {
        mutableStateOf(true)
    }

    val showTopPickDetails = rememberSaveable {
        mutableStateOf(false)
    }

    val focusedParentId = rememberSaveable {
        mutableStateOf("")
    }

    val focusedChildId = rememberSaveable {
        mutableStateOf("")
    }

    val restoreFocusVersion = rememberSaveable {
        mutableStateOf(0)
    }

    val focusRequester = remember { FocusRequester() }

    val coroutineScope = rememberCoroutineScope()
    val homeState by homeViewModel.homeState.collectAsState()

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (
                event == Lifecycle.Event.ON_RESUME &&
                focusedParentId.value.isNotBlank() &&
                focusedChildId.value.isNotBlank()
            ) {
                restoreFocusVersion.value += 1
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(Modifier.fillMaxSize()) {
        AnimatedVisibility(showCarousel.value) {
            HeroItem(state = heroItemState, modifier = Modifier.focusRequester(focusRequester))
        }
        AnimatedVisibility(showTopPickDetails.value) {
            TopPickDetails(modifier = Modifier
                .padding(start = 52.dp, end = 52.dp, bottom = 32.dp)
                .height(200.dp), title = "Top Picks", metadata = "SVT Play  •  2021  •  1h 30m")
        }
        HomeCarousel(
            homeState = homeState,
            focusedParentId = focusedParentId.value,
            focusedChildId = focusedChildId.value,
            focusedParentIndex = focusState.value.first,
            focusedChildIndex = focusState.value.second,
            restoreFocusVersion = restoreFocusVersion.value,
            modifier = Modifier
                .handleDPadKeyEvents(
                    onUp = {
                        coroutineScope.launch {
                            if (focusState.value.first == 0) {
                                showCarousel.value = true
                                showTopPickDetails.value = false
                                delay(200)
                                focusRequester.requestFocus()
                            }
                        }
                    },
                )
                .onFocusChanged {
                    if (it.hasFocus.not()) {
                        showCarousel.value = true
                        showTopPickDetails.value = false
                    }
                }
                .weight(1f), onItemFocus = { parent, child ->

                val parentIndex = homeState.findIndexById(parentId = parent, childId = child)

                focusState.value = FocusPosition(parentIndex.first, parentIndex.second)
                focusedParentId.value = parent
                focusedChildId.value = child
                onItemFocus(parent, child)

                if (parentIndex.first == 0) {
                    showCarousel.value = false
                    showTopPickDetails.value = true
                } else {
                    showCarousel.value = false
                    showTopPickDetails.value = false
                }

//                navigationBar(NavigationEvent.TopBar)
            },
            onItemClick = onItemClick,
        )
    }
}

@Composable
fun TopPickDetails(
    title: String,
    metadata: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .wrapContentSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AnimatedContent(title) {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Black
                    ),
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            AnimatedContent(metadata) {
                Text(
                    modifier = Modifier.graphicsLayer { alpha = 0.5f },
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

typealias FocusPosition = Pair<Int, Int>
