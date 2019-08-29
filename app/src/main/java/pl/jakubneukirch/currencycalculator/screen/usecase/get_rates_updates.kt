package pl.jakubneukirch.currencycalculator.screen.usecase

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import pl.jakubneukirch.currencycalculator.base.UseCase
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable
import pl.jakubneukirch.currencycalculator.data.repository.CurrencyRepository
import java.util.concurrent.TimeUnit


interface IGetRatesUpdates : UseCase<UseCase.None, Observable<RatesTable>>

/**
 * UseCase responsible for periodically update currency values
 */
class GetRatesUpdates constructor(private val _currencyRepository: CurrencyRepository) : IGetRatesUpdates {
    override fun run(params: UseCase.None): Observable<RatesTable> {
        return Observable.interval(REFRESH_INTERVAL, TimeUnit.SECONDS)
            .flatMapSingle { _currencyRepository.getRates() }
        //todo implement in offline mode.onErrorReturn {  }
    }

    companion object {
        /**
         * Refresh delay in Seconds
         */
        private const val REFRESH_INTERVAL = 1L
    }
}