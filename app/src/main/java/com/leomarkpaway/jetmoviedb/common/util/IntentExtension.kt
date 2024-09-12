package com.leomarkpaway.jetmoviedb.common.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri

inline fun <reified T : Activity> Context.createIntent(): Intent = Intent(this, T::class.java)

fun String.createBrowserIntent() = Intent(Intent.ACTION_VIEW, Uri.parse(this))