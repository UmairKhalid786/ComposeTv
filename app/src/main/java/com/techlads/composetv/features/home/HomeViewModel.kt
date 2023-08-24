package com.techlads.composetv.features.home

import androidx.lifecycle.ViewModel
import com.techlads.composetv.features.home.leftmenu.data.MenuData
import com.techlads.composetv.features.home.leftmenu.model.MenuItem
import com.techlads.composetv.utils.toMutable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val menuItems: StateFlow<List<MenuItem>> = MutableStateFlow(emptyList())
    val menuState: StateFlow<Boolean> = MutableStateFlow(false)

    init {
        menuItems.toMutable().value = MenuData.menuItems
    }

    fun menuClosed() {
        menuState.toMutable().value = false
    }

    fun menuOpen() {
        menuState.toMutable().value = true
    }
}
