package pl.jakubneukirch.currencycalculator.viewmodel

import com.jraska.livedata.test
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import pl.jakubneukirch.currencycalculator.base.BaseViewModelTest
import pl.jakubneukirch.currencycalculator.base.UseCase
import pl.jakubneukirch.currencycalculator.data.model.view.Rate
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable
import pl.jakubneukirch.currencycalculator.screen.rates.RatesViewModel
import pl.jakubneukirch.currencycalculator.usecase.IGetRatesUpdates

class RatesViewModelTest : BaseViewModelTest<RatesViewModel>() {
    override lateinit var viewModel: RatesViewModel
    private lateinit var _getRatesUpdates: IGetRatesUpdates

    @Before
    fun setup() {
        _getRatesUpdates = mockk()
        viewModel = RatesViewModel(_getRatesUpdates)
        viewModel.onStart()
    }

    @Test
    fun `should update rates 3 times`() {
        val exampleData = RatesTable(Rate.baseRate("Eur"), listOf())

        every {
            _getRatesUpdates(UseCase.None)
        } returns Observable.just(exampleData, exampleData, exampleData)

        val testObserver = viewModel.ratesTable.test()

        viewModel.listenToRatesChanges()

        testObserver.assertValueHistory(exampleData, exampleData, exampleData)
    }

    @Test
    fun `should not update on error`() {
        every {
            _getRatesUpdates(UseCase.None)
        } returns Observable.error(Exception("Error"))

        viewModel.listenToRatesChanges()

        viewModel.ratesTable
            .test()
            .assertNoValue()
    }

    @After
    fun tearDown() {
        viewModel.onStop()
    }
}