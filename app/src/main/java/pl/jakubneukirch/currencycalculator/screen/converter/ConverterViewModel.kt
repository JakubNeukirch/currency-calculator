package pl.jakubneukirch.currencycalculator.screen.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.plusAssign
import pl.jakubneukirch.currencycalculator.base.BaseViewModel
import pl.jakubneukirch.currencycalculator.base.UseCase
import pl.jakubneukirch.currencycalculator.data.model.view.ConvertedCurrency
import pl.jakubneukirch.currencycalculator.usecase.IGetRatesUpdates

class ConverterViewModel(private val _getRatesUpdates: IGetRatesUpdates) : BaseViewModel() {

    private val _convertedCurrencies = MutableLiveData<ConvertedCurrency>()
    val convertedCurrencies: LiveData<ConvertedCurrency>
        get() = _convertedCurrencies

    private var _sourceCurrency: ConvertedCurrency? = null

    fun setSourceRateValue(currency: ConvertedCurrency) {
        _sourceCurrency = currency

    }

    private fun calculateValues() {
        onStopDisposables += _getRatesUpdates(UseCase.None)
            .subscribe()
    }
}