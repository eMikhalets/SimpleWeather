package com.emikhalets.simpleweather.utils

import java.text.SimpleDateFormat
import java.util.*

// TODO: write tests
fun Calendar.getDayOfWeekNamesStartToday(daysCount: Int): List<String> {
    val weekNames = mutableListOf<String>()
    var dayNum = this.get(Calendar.DAY_OF_WEEK)
    repeat(daysCount) {
        this.set(Calendar.DAY_OF_WEEK, dayNum)
        weekNames.add(SimpleDateFormat("E", Locale.getDefault()).format(this))
        dayNum = if (dayNum == 6) 0 else dayNum + 1
    }

    return weekNames
}