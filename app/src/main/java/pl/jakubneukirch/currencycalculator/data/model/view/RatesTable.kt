package pl.jakubneukirch.currencycalculator.data.model.view

import pl.jakubneukirch.currencycalculator.data.model.api.RatesResponse

data class RatesTable(
    val baseRate: Rate,
    val rates: List<Rate>
) {

    /**
     * Creates list of all rates, including base rate.
     */
    val allRates: List<Rate>
        get() = listOf(baseRate, *rates.toTypedArray())

    companion object {
        fun fromApiResponse(response: RatesResponse): RatesTable {
            return RatesTable(
                baseRate = Rate.baseRate(response.base),
                rates = response.rates.map { entry ->
                    Rate(entry.key, entry.value)
                }
            )
        }
    }

}