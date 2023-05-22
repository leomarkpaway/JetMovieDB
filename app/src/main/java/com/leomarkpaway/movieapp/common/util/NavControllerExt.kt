package com.leomarkpaway.movieapp.common.util

import androidx.navigation.NavController

fun NavController.safeNavigate(actionId: Int) {
    currentDestination?.getAction(actionId)?.let { navigate(actionId) }
}