package pl.jakubneukirch.currencycalculator.repository

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import pl.jakubneukirch.currencycalculator.base.BaseRepositoryTest
import pl.jakubneukirch.currencycalculator.data.CurrencyDetailsProvider
import pl.jakubneukirch.currencycalculator.data.api.CurrencyApi
import pl.jakubneukirch.currencycalculator.data.model.api.RatesResponse
import pl.jakubneukirch.currencycalculator.data.model.view.Currency
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable
import pl.jakubneukirch.currencycalculator.data.repository.CurrencyRepository
import java.net.SocketException
import java.util.*

class CurrencyRepositoryTest : BaseRepositoryTest<CurrencyRepository>() {
    override lateinit var repository: CurrencyRepository
    private lateinit var _currencyApi: CurrencyApi
    private lateinit var _currencyDetailsProvider: CurrencyDetailsProvider

    @Before
    fun setup() {
        _currencyApi = mockk()
        _currencyDetailsProvider = mockk()
        repository = CurrencyRepository(_currencyApi, _currencyDetailsProvider)
    }

    @Test
    fun `should correctly map data`() {
        val inputData = RatesResponse("EUR", Date(), mapOf("PLN" to 4.3, "AUD" to 1.6))
        val expectedData = RatesTable(
            Currency("EUR", 1.0, 1, 1),
            listOf(
                Currency("PLN", 4.3, 1, 1),
                Currency("AUD", 1.6, 1, 1)
            )
        )

        every {
            _currencyDetailsProvider.getCurrencyDetailsFor(any())
        } returns CurrencyDetailsProvider.CurrencyDetails(1, 1)
        every {
            _currencyApi.getLatestRates()
        } returns Single.just(inputData)

        repository.getRates()
            .test()
            .assertValue(expectedData)
    }

    @Test
    fun `should be error`() {
        val expectedError = SocketException("No internet connection")

        every {
            _currencyApi.getLatestRates()
        } returns Single.error(expectedError)

        repository.getRates()
            .test()
            .assertError(expectedError)
    }
}