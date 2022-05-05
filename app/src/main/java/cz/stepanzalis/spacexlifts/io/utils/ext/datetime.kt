package cz.stepanzalis.spacexlifts.io.utils.ext

import java.text.SimpleDateFormat
import java.util.*

private val locale by lazy { Locale.getDefault() }

fun Long.parseDateAndTimeWithMillis(): String =
    SimpleDateFormat("yyyy-MM-dd-HH:mm:ss:SSS", locale).format(this)

fun Long.parseToHumanReadableDate(): String =
    SimpleDateFormat("d. M. yyyy", locale).format(this)

fun Long.parseToHumanReadableDateTime(): String =
    SimpleDateFormat("d. M. yyyy HH:mm", locale).format(this)
