package com.leomarkpaway.jetmoviedb.common.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun LifecycleOwner.launchWhenCreated(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launchWhenCreated { block() }
}

fun LifecycleOwner.launchWhenStarted(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launchWhenStarted { block() }
}

fun LifecycleOwner.launchWhenResumed(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launchWhenResumed() { block() }
}

fun LifecycleOwner.launchOnIO(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(Dispatchers.IO) { block() }
}

fun LifecycleOwner.launchOnMain(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(Dispatchers.Main) { block() }
}

fun LifecycleOwner.launchOnDefault(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(Dispatchers.Default) { block() }
}

fun LifecycleOwner.launchOnUnconfined(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(Dispatchers.Unconfined) { block() }
}

fun LifecycleOwner.launch(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch { block() }
}