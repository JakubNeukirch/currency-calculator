package pl.jakubneukirch.currencycalculator.data.model.view

/**
 * Currency and value and its converted value
 */
data class ConvertedCurrency(
    /**
     * Currency and its data
     */
    val currency: Currency,
    /**
     * Converted value of currency based
     */
    var value: Double
)