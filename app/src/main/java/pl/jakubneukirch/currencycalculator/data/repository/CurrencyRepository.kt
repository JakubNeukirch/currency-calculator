package pl.jakubneukirch.currencycalculator.data.repository

import io.reactivex.Single
import pl.jakubneukirch.currencycalculator.data.CurrencyDetailsProvider
import pl.jakubneukirch.currencycalculator.data.api.CurrencyApi
import pl.jakubneukirch.currencycalculator.data.db.dao.CurrencyDao
import pl.jakubneukirch.currencycalculator.data.model.api.RatesResponse
import pl.jakubneukirch.currencycalculator.data.model.db.CurrencyDb
import pl.jakubneukirch.currencycalculator.data.model.view.Currency
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val _currencyApi: CurrencyApi,
    private val _currencyDetailsProvider: CurrencyDetailsProvider,
    private val _currencyDao: CurrencyDao
) {

    /**
     * Loads currency rates and saves them locally, finally returns local currency rates.
     * If error occurs during loading from API, only local rates are loaded
     */
    fun getRates(): Single<RatesTable> {
        return _currencyApi.getLatestRates()
            .map { ratesResponse -> ratesResponse.toCurrenciesDb() }
            .onErrorReturn { listOf() }
            .flatMap { currencies -> saveCurrenciesLocally(currencies) }
            .map { currencies -> currencies.toRatesTable() }
    }

    private fun RatesResponse.toCurrenciesDb(): List<CurrencyDb> {
        return this
            .rates
            .toCurrencies()
            .toMutableList()
            .apply {
                add(0, createCurrency(this@toCurrenciesDb.base, Currency.BASE_RATE, true))
            }
    }

    private fun saveCurrenciesLocally(currencies: List<CurrencyDb>): Single<List<CurrencyDb>> {
        return _currencyDao.insertAll(currencies)
            .flatMap { _currencyDao.selectAll() }
    }

    private fun List<CurrencyDb>.toRatesTable(): RatesTable {
        val currencies = this
            .map { currencyDb -> currencyDb.toCurrency() }
        return RatesTable(
            currencies
        )
    }

    private fun CurrencyDb.toCurrency(): Currency {
        val currencyDetails = _currencyDetailsProvider.getCurrencyDetailsFor(abbreviation)
        return Currency(
            abbreviation,
            rate,
            currencyDetails.nameId,
            currencyDetails.flagId
        )
    }

    private fun Map<String, Double>.toCurrencies(): List<CurrencyDb> {
        return this.map { (abbreviation, rate) ->
            createCurrency(abbreviation, rate)
        }
    }

    private fun createCurrency(
        abbreviation: String,
        rate: Double,
        isBase: Boolean = false
    ): CurrencyDb {
        return CurrencyDb(
            abbreviation = abbreviation,
            rate = rate,
            isBase = isBase
        )
    }
}