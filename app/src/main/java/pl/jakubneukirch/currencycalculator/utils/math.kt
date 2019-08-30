package pl.jakubneukirch.currencycalculator.utils

import kotlin.math.roundToInt

fun Double.roundDecimalPlace(): Double {
    return (this * 100).roundToInt() / 100.0
}