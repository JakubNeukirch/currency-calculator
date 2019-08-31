package pl.jakubneukirch.currencycalculator.data.model.view

/**
 * Table with current rates of currencies
 */
data class RatesTable(
    /**
     * Actual currency rates
     */
    val currencies: List<Currency>
)