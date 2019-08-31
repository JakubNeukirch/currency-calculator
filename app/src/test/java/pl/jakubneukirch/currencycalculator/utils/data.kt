package pl.jakubneukirch.currencycalculator.utils

import pl.jakubneukirch.currencycalculator.data.model.view.Currency

fun baseRate(abbreviation: String): Currency {
    return Currency(
        abbreviation,
        Currency.BASE_RATE
    )
}