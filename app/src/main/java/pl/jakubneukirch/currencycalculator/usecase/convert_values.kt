package pl.jakubneukirch.currencycalculator.usecase

import io.reactivex.Single
import pl.jakubneukirch.currencycalculator.base.UseCase
import pl.jakubneukirch.currencycalculator.data.model.view.ConvertedCurrency
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable
import pl.jakubneukirch.currencycalculator.utils.roundDecimalPlace

interface IConvertValues : UseCase<IConvertValues.Params, Single<List<ConvertedCurrency>>> {
    data class Params(
        val sourceCurrency: ConvertedCurrency,
        val rates: RatesTable
    )
}

/**
 * UseCase which converts currency values based on sourceCurrency which is currency on which should be
 * based further calculations.
 * @param sourceCurrency    ConvertedCurrency on which calculations are based
 * @param rates             RatesTable with current currency values
 */
class ConvertValues : IConvertValues {
    override fun run(params: IConvertValues.Params): Single<List<ConvertedCurrency>> {
        return Single.create {
            try {
                val convertedCurrencies: List<ConvertedCurrency> = convertValues(
                    params.sourceCurrency,
                    params.rates
                )

                it.onSuccess(convertedCurrencies)
            } catch (ex: Exception) {
                it.onError(ex)
            }
        }
    }

    private fun convertValues(
        sourceCurrency: ConvertedCurrency,
        rates: RatesTable
    ): List<ConvertedCurrency> {
        val baseRateValue = sourceCurrency.value / sourceCurrency.currency.rate
        return rates.allCurrencies.map { currency ->
            return@map if (currency == sourceCurrency.currency) {
                sourceCurrency
            } else {
                ConvertedCurrency(
                    currency = currency,
                    value = convertValue(baseRateValue, currency.rate)
                )
            }
        }
    }

    private fun convertValue(baseRateValue: Double, currencyRate: Double) : Double {
        return (baseRateValue * currencyRate).roundDecimalPlace()
    }
}