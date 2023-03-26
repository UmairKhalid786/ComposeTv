package com.techlads.composetv.home

import androidx.lifecycle.ViewModel
import com.techlads.composetv.leftmenu.model.MenuItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    val menuItems : StateFlow<List<MenuItem>> = MutableStateFlow(emptyList())

    init {
        menuItems.toMutable().value = listOf()
    }
}

fun <T> StateFlow<T>.toMutable() = this as MutableStateFlow