package pl.jakubneukirch.currencycalculator.viewmodel

import com.jraska.livedata.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import pl.jakubneukirch.currencycalculator.base.BaseViewModelTest
import pl.jakubneukirch.currencycalculator.data.model.view.ConvertedCurrency
import pl.jakubneukirch.currencycalculator.data.model.view.Currency
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable
import pl.jakubneukirch.currencycalculator.screen.converter.ConverterViewModel
import pl.jakubneukirch.currencycalculator.usecase.ConvertValues
import pl.jakubneukirch.currencycalculator.usecase.IGetRatesUpdates
import kotlin.test.assertFailsWith

class ConverterViewModelTest : BaseViewModelTest<ConverterViewModel>() {
    override lateinit var viewModel: ConverterViewModel
    private lateinit var _getRatesUpdates: IGetRatesUpdates
    private lateinit var _convertValues: ConvertValues

    @Before
    fun setup() {
        _getRatesUpdates = mockk()
        _convertValues = mockk()

        viewModel = ConverterViewModel(
            _getRatesUpdates,
            _convertValues
        )
        viewModel.onStart()
    }

    @After
    fun tearDown() {
        viewModel.onStop()
    }

    @Test
    fun `should convert values on update rates`() {
        val testRates = RatesTable(
            Currency.baseRate("EUR"),
            listOf(Currency("PLN", 4.33))
        )
        every { _getRatesUpdates(any()) } returns Observable.just(testRates, testRates)
        every { _convertValues(any()) } returns Single.just(listOf())
        viewModel.listenToRatesChanges()

        verify(exactly = 2) { _convertValues(any()) }
    }

    @Test
    fun `should not convert values on update rates error`() {
        every { _getRatesUpdates(any()) } returns Observable.error(Exception("error"))
        every { _convertValues(any()) } returns Single.just(listOf())
        viewModel.listenToRatesChanges()

        verify(exactly = 0) { _convertValues(any()) }
    }

    @Test
    fun `should convert values on source currency change`() {
        val currencyPln = Currency("PLN", 4.33)
        val testRates = RatesTable(
            Currency.baseRate("EUR"),
            listOf(currencyPln)
        )

        every { _getRatesUpdates(any()) } returns Observable.just(testRates)
        every { _convertValues(any()) } returns Single.just(listOf())

        viewModel.listenToRatesChanges()
        viewModel.setSourceCurrency(ConvertedCurrency(currencyPln, 8.0))

        verify(exactly = 2) { _convertValues(any()) }
    }

    @Test
    fun `should not change convertedCurrencies value on convert error`() {
        val currencyPln = Currency("PLN", 4.33)
        val testRates = RatesTable(
            Currency.baseRate("EUR"),
            listOf(currencyPln)
        )

        every { _getRatesUpdates(any()) } returns Observable.just(testRates)
        every { _convertValues(any()) } returns Single.error(Exception())

        viewModel.listenToRatesChanges()

        viewModel.convertedCurrencies
            .test()
            .assertNoValue()
    }

    @Test
    fun `should throw UninitializedPropertyAccessException when no rates loaded`() {
        val currencyPln = Currency("PLN", 4.33)

        every { _getRatesUpdates(any()) } returns Observable.error(Exception("error"))
        every { _convertValues(any()) } returns Single.just(listOf())

        assertFailsWith<UninitializedPropertyAccessException>{
            viewModel.setSourceCurrency(ConvertedCurrency(currencyPln, 8.0))
        }
    }
}