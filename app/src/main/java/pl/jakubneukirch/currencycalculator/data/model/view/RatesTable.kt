package pl.jakubneukirch.currencycalculator.data.model.view

/**
 * Table with current rates of currencies
 */
data class RatesTable(
    /**
     * Currency on which rates are based
     */
    val baseCurrency: Currency,
    /**
     * Actual currency rates
     */
    val currencies: List<Currency>
) {

    /**
     * Creates list of all currencies, including base currency.
     */
    val allCurrencies: List<Currency>
        get() = listOf(baseCurrency, *currencies.toTypedArray())
}