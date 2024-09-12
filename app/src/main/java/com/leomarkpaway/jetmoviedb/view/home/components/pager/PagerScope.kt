package com.leomarkpaway.jetmoviedb.view.home.components.pager

import com.leomarkpaway.jetmoviedb.view.home.states.SelectionState

class PagerScope(
    private val state: PagerState,
    val commingPage: Int
) {

    val currentPage: Int
        get() = state.currentPage

    val currentPageOffset: Float
        get() = state.currentPageOffset

    val selectionState: SelectionState
        get() = state.selectionState
}