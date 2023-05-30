package com.leomarkpaway.movieapp.common.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun ByteArray.convertByteArrayToBitmap(): Bitmap = BitmapFactory.decodeByteArray(this, 0, this.size)