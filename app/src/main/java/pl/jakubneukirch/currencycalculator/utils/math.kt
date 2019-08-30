package pl.jakubneukirch.currencycalculator.utils

fun Double.roundDecimalPlace(): Double {
    return (this * 100).toInt() / 100.0
}