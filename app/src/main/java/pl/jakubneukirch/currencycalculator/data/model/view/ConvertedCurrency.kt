package pl.jakubneukirch.currencycalculator.data.model.view

/**
 * Currency and value and its converted value
 */
data class ConvertedCurrency(
    val currency: Currency,
    /**
     * Converted value of currency based
     */
    var value: Double
)