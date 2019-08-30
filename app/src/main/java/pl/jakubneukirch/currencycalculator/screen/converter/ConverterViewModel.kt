package pl.jakubneukirch.currencycalculator.screen.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import pl.jakubneukirch.currencycalculator.base.BaseViewModel
import pl.jakubneukirch.currencycalculator.base.UseCase
import pl.jakubneukirch.currencycalculator.data.model.view.ConvertedCurrency
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable
import pl.jakubneukirch.currencycalculator.usecase.IConvertValues
import pl.jakubneukirch.currencycalculator.usecase.IGetRatesUpdates
import pl.jakubneukirch.currencycalculator.utils.useStandardSchedulers
import timber.log.Timber

class ConverterViewModel(
    private val _getRatesUpdates: IGetRatesUpdates,
    private val _convertValues: IConvertValues
) : BaseViewModel() {

    private val _convertedCurrencies = MutableLiveData<List<ConvertedCurrency>>()
    val convertedCurrencies: LiveData<List<ConvertedCurrency>>
        get() = _convertedCurrencies

    private lateinit var _ratesTable: RatesTable
    private var _sourceCurrency: ConvertedCurrency? = null

    fun listenToRatesChanges() {
        onStopDisposables += _getRatesUpdates(UseCase.None)
            .useStandardSchedulers()
            .subscribeBy(
                onNext = { ratesTable ->
                    _ratesTable = ratesTable
                    _sourceCurrency = _sourceCurrency ?: ConvertedCurrency(
                        ratesTable.baseCurrency,
                        ratesTable.baseCurrency.rate
                    )
                    calculateValues()
                },
                onError = {
                    Timber.e(it)
                }
            )
    }

    fun setSourceCurrency(currency: ConvertedCurrency) {
        _sourceCurrency = currency
        calculateValues()
    }

    private fun calculateValues() {
        onStopDisposables += _convertValues(IConvertValues.Params(_sourceCurrency!!, _ratesTable))
            .useStandardSchedulers()
            .subscribeBy(
                onSuccess = { converted ->
                    _convertedCurrencies.value = converted
                },
                onError = {
                    Timber.e(it)
                }
            )
    }
}