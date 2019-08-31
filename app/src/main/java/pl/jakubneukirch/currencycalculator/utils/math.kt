package pl.jakubneukirch.currencycalculator.utils

import kotlin.math.roundToInt

fun Double.roundDecimalPlace(): Double {
    return (this * 100).roundToInt() / 100.0
}

fun Double.removeZeros(): Number {
    val rounded = this.roundDecimalPlace()
    return if (rounded % 1.0 == 0.0) {
        rounded.roundToInt()
    } else {
        rounded
    }
}