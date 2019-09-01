package pl.jakubneukirch.currencycalculator.data.model.view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import pl.jakubneukirch.currencycalculator.R

data class Currency(
    val abbreviation: String,
    val rate: Double,
    @StringRes
    val nameId: Int = R.string.currency_name_unknown,
    @DrawableRes
    val flagId: Int = R.drawable.earth
) {
    companion object {
        const val BASE_RATE = 1.0
    }
}