package pl.jakubneukirch.currencycalculator.data.model.view

import pl.jakubneukirch.currencycalculator.data.model.api.RatesResponse

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

    companion object {
        fun fromApiResponse(response: RatesResponse): RatesTable {
            return RatesTable(
                baseCurrency = Currency.baseRate(response.base),
                currencies = response.rates.map { entry ->
                    Currency(entry.key, entry.value)
                }
            )
        }
    }

}