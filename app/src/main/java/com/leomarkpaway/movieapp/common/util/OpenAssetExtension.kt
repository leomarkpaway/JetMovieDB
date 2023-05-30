package com.leomarkpaway.movieapp.common.util

import android.content.Context

fun Context.openAsset(fileName: String) = assets.open(fileName)