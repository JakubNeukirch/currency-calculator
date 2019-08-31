package pl.jakubneukirch.currencycalculator.data.repository

import io.reactivex.Single
import pl.jakubneukirch.currencycalculator.data.CurrencyApi
import pl.jakubneukirch.currencycalculator.data.CurrencyDetailsProvider
import pl.jakubneukirch.currencycalculator.data.model.view.Currency
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val _currencyApi: CurrencyApi,
    private val _currencyDetailsProvider: CurrencyDetailsProvider
) {

    fun getRates(): Single<RatesTable> {
        return _currencyApi.getLatestRates()
            .map { ratesResponse ->
                RatesTable(
                    createCurrency(ratesResponse.base, Currency.BASE_RATE),
                    ratesToCurrencies(ratesResponse.rates)
                )
            }
    }

    private fun ratesToCurrencies(rates: Map<String, Double>): List<Currency> {
        return rates.map { (abbreviation, rate) ->
            createCurrency(abbreviation, rate)
        }
    }

    private fun createCurrency(abbreviation: String, rate: Double): Currency {
        val currencyDetails = _currencyDetailsProvider.getCurrencyDetailsFor(abbreviation)
        return Currency(
            abbreviation = abbreviation,
            rate = rate,
            nameId = currencyDetails.nameId,
            flagId = currencyDetails.flagId
        )
    }

}