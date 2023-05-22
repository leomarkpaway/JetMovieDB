package com.leomarkpaway.movieapp.common.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// TODO make collect latest Data for shared flow
fun <T> StateFlow<T>.collectLatestData(scope: CoroutineScope, action: (T) -> Unit): Job {
    return scope.launch {
        this@collectLatestData.collectLatest { data -> action(data) }
    }
}