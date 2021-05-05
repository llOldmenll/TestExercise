package com.exercise.domain.utils

import java.text.SimpleDateFormat
import java.util.Locale

// Default input date sample - 2021-06-25T06:25:00.000
// Default output date sample - 25.06.2021 6:25
fun String.toFormattedDate(
    inputFormat: String = "yyyy-MM-dd'T'HH:mm",
    outputFormat: String = "HH:mm",
    locale: Locale = Locale.getDefault()
): String {
    if (this.isBlank()) return this

    val parser = SimpleDateFormat(inputFormat, locale)
    val formatter = SimpleDateFormat(outputFormat, locale)
    return formatter.format(parser.parse(this))
}