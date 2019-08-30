package pl.jakubneukirch.currencycalculator.data.model.view

/**
 * Rate and value and its converted value
 */
class ConvertedRate(
    /**
     * Currency rate
     */
    val rate: Rate,
    /**
     * Converted value of currency based
     */
    var value: Double
)