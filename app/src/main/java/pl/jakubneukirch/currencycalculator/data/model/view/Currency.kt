package pl.jakubneukirch.currencycalculator.data.model.view

import androidx.annotation.DrawableRes
import pl.jakubneukirch.currencycalculator.R

data class Currency(
    val abbreviation: String,
    val rate: Double,
    val name: String = "US Dollars", //todo set real name
    @DrawableRes
    val flagId: Int = R.drawable.afg
) {
    companion object {
        private const val BASE_VALUE = 1.0

        fun baseRate(name: String): Currency {
            return Currency(
                abbreviation = name,
                rate = BASE_VALUE
            )
        }
    }
}