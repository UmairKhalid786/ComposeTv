package com.techlads.composetv.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


fun <T> StateFlow<T>.toMutable() = this as MutableStateFlow