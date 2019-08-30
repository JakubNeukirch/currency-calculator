package pl.jakubneukirch.currencycalculator.screen.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import pl.jakubneukirch.currencycalculator.base.BaseViewModel
import pl.jakubneukirch.currencycalculator.base.UseCase
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable
import pl.jakubneukirch.currencycalculator.usecase.IGetRatesUpdates
import pl.jakubneukirch.currencycalculator.utils.useStandardSchedulers
import timber.log.Timber

class RatesViewModel(private val _getRatesUpdates: IGetRatesUpdates) : BaseViewModel() {

    private val _ratesTable = MutableLiveData<RatesTable>()
    val ratesTable: LiveData<RatesTable>
        get() = _ratesTable

    fun listenToRatesChanges() {
        onStopDisposables += _getRatesUpdates(UseCase.None)
            .useStandardSchedulers()
            .subscribeBy(
                onNext = { rates ->
                    _ratesTable.value = rates
                },
                onError = {
                    Timber.e(it)
                }
            )
    }
}