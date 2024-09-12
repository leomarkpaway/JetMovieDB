package com.leomarkpaway.jetmoviedb.common.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import java.util.Date

fun convertDateToMillis(dateString: String): Long {
    val pattern = "dd MMMM yyyy"
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getDefault()

    val date: Date? = simpleDateFormat.parse(dateString)
    return date?.time ?: 0
}

fun formatDateFromMillis(millis: Long): String {
    val pattern = "dd MMMM yyyy"
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    val date = Date(millis)
    return dateFormat.format(date)
}