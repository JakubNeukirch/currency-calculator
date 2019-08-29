package pl.jakubneukirch.currencycalculator.usecase

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import pl.jakubneukirch.currencycalculator.base.BaseUseCaseTest
import pl.jakubneukirch.currencycalculator.base.UseCase
import pl.jakubneukirch.currencycalculator.data.model.view.Rate
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable
import pl.jakubneukirch.currencycalculator.data.repository.CurrencyRepository
import pl.jakubneukirch.currencycalculator.screen.usecase.GetRatesUpdates
import pl.jakubneukirch.currencycalculator.screen.usecase.IGetRatesUpdates
import java.util.concurrent.TimeUnit

class GetRatesUpdatesTest:BaseUseCaseTest<IGetRatesUpdates>() {
    override lateinit var useCase: IGetRatesUpdates
    private lateinit var _currencyRepository: CurrencyRepository

    override fun setup() {
        super.setup()
        _currencyRepository = mockk()
        useCase = GetRatesUpdates(_currencyRepository)
    }

    @Test
    fun `should update 3 times`() {
        val testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        val inputData = RatesTable(Rate("EUR", 1.0), listOf())

        every { _currencyRepository.getRates() } returns Single.just(inputData)

        val testObservable = useCase(UseCase.None).test()
        testScheduler.advanceTimeBy(3, TimeUnit.SECONDS)
        testObservable.assertValues(inputData, inputData, inputData)
    }
}
