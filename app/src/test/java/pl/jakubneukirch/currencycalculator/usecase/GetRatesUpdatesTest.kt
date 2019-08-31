package pl.jakubneukirch.currencycalculator.usecase

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import pl.jakubneukirch.currencycalculator.base.BaseUseCaseTest
import pl.jakubneukirch.currencycalculator.base.UseCase
import pl.jakubneukirch.currencycalculator.data.model.view.Currency
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable
import pl.jakubneukirch.currencycalculator.data.repository.CurrencyRepository
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
    fun `should update 3 times in 2 seconds`() {
        val testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        val inputData = RatesTable(listOf(Currency("EUR", 1.0)))

        every { _currencyRepository.getRates() } returns Single.just(inputData)

        val testObservable = useCase(UseCase.None).test()
        testScheduler.advanceTimeTo(2, TimeUnit.SECONDS)

        testObservable.assertValues(inputData, inputData, inputData)
    }

    @Test
    fun `should update once in less than 1 second`() {
        val testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        val inputData = RatesTable(listOf(Currency("EUR", 1.0)))

        every { _currencyRepository.getRates() } returns Single.just(inputData)

        val testObservable = useCase(UseCase.None).test()
        testScheduler.advanceTimeTo(300, TimeUnit.MILLISECONDS)

        testObservable.assertValues(inputData)
    }
}
