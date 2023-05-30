package com.leomarkpaway.movieapp.common.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getCurrentDate():String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        return sdf.format(Date())
    }

    fun String.getConvertedDate(): String {
        val dateFormat = SimpleDateFormat("yyyy, dd MMMM", Locale.getDefault())
        return dateFormat.format(
            SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault()
            ).parse(this)
        )
    }

}