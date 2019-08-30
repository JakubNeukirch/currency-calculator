package pl.jakubneukirch.currencycalculator.usecase

import io.reactivex.Single
import pl.jakubneukirch.currencycalculator.base.UseCase
import pl.jakubneukirch.currencycalculator.data.model.view.ConvertedRate
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable

interface IConvertValues : UseCase<IConvertValues.Params, Single<List<ConvertedRate>>> {
    data class Params(
        val sourceRate: ConvertedRate,
        val rates: RatesTable
    )
}

class ConvertValues : IConvertValues {
    override fun run(params: IConvertValues.Params): Single<List<ConvertedRate>> {
        return Single.create {
            try {
                val convertedRates: List<ConvertedRate> = convertValues(
                    params.sourceRate,
                    params.rates
                )

                it.onSuccess(convertedRates)
            } catch (ex: Exception) {
                it.onError(ex)
            }
        }
    }

    private fun convertValues(sourceRate: ConvertedRate, rates: RatesTable): List<ConvertedRate> {
        val baseRateValue = sourceRate.value / sourceRate.rate.value
        return rates.allRates.map { rate ->
            ConvertedRate(
                rate = rate,
                value = baseRateValue * rate.value
            )
        }
    }
}