package pl.jakubneukirch.currencycalculator.screen.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.plusAssign
import pl.jakubneukirch.currencycalculator.base.BaseViewModel
import pl.jakubneukirch.currencycalculator.base.UseCase
import pl.jakubneukirch.currencycalculator.data.model.view.ConvertedRate
import pl.jakubneukirch.currencycalculator.usecase.IGetRatesUpdates

class ConverterViewModel(private val _getRatesUpdates: IGetRatesUpdates) : BaseViewModel() {

    private val _convertedRates = MutableLiveData<ConvertedRate>()
    val convertedRates: LiveData<ConvertedRate>
        get() = _convertedRates

    private var _sourceRate: ConvertedRate? = null

    fun setSourceRateValue(rate: ConvertedRate) {
        _sourceRate = rate

    }

    private fun calculateValues() {
        onStopDisposables += _getRatesUpdates(UseCase.None)
            .subscribe()
    }
}