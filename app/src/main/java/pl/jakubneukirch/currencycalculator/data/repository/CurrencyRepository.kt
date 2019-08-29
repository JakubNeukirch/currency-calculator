package pl.jakubneukirch.currencycalculator.data.repository

import io.reactivex.Single
import pl.jakubneukirch.currencycalculator.data.CurrencyApi
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val _currencyApi: CurrencyApi) {

    fun getRates(): Single<RatesTable> {
        return _currencyApi.getLatestRates()
            .map { ratesResponse ->
                RatesTable.fromApiResponse(ratesResponse)
            }
    }

}