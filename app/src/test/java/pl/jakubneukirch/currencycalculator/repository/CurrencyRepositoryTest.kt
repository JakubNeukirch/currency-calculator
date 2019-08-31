package pl.jakubneukirch.currencycalculator.repository

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import pl.jakubneukirch.currencycalculator.base.BaseRepositoryTest
import pl.jakubneukirch.currencycalculator.data.CurrencyDetailsProvider
import pl.jakubneukirch.currencycalculator.data.api.CurrencyApi
import pl.jakubneukirch.currencycalculator.data.db.dao.CurrencyDao
import pl.jakubneukirch.currencycalculator.data.model.api.RatesResponse
import pl.jakubneukirch.currencycalculator.data.model.db.CurrencyDb
import pl.jakubneukirch.currencycalculator.data.model.view.Currency
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable
import pl.jakubneukirch.currencycalculator.data.repository.CurrencyRepository
import java.net.SocketException
import java.util.*

class CurrencyRepositoryTest : BaseRepositoryTest<CurrencyRepository>() {
    override lateinit var repository: CurrencyRepository
    private lateinit var _currencyApi: CurrencyApi
    private lateinit var _currencyDetailsProvider: CurrencyDetailsProvider
    private lateinit var _currencyDao: CurrencyDao

    @Before
    fun setup() {
        _currencyApi = mockk()
        _currencyDetailsProvider = mockk {
            every {
                getCurrencyDetailsFor(any())
            } returns CurrencyDetailsProvider.CurrencyDetails(1, 1)
        }
        _currencyDao = mockk()
        repository = CurrencyRepository(_currencyApi, _currencyDetailsProvider, _currencyDao)
    }

    @Test
    fun `should correctly map data`() {
        val inputData = RatesResponse("EUR", Date(), mapOf("PLN" to 4.3, "AUD" to 1.6))
        val expectedData = RatesTable(
            listOf(
                Currency("EUR", 1.0, 1, 1),
                Currency("PLN", 4.3, 1, 1),
                Currency("AUD", 1.6, 1, 1)
            )
        )

        every {
            _currencyApi.getLatestRates()
        } returns Single.just(inputData)
        every { _currencyDao.insertAll(any()) } returns Single.just(listOf())
        every { _currencyDao.selectAll() } returns Single.just(
            listOf(
                CurrencyDb("EUR", 1.0, true),
                CurrencyDb("PLN", 4.3, false),
                CurrencyDb("AUD", 1.6, false)
            )
        )

        repository.getRates()
            .test()
            .assertValue(expectedData)
    }

    @Test
    fun `should be local list on api exception`() {
        val expectedError = SocketException("No internet connection")
        val currencyEuro = CurrencyDb("EUR", 1.0, true)

        every {
            _currencyApi.getLatestRates()
        } returns Single.error(expectedError)
        every { _currencyDao.selectAll() } returns Single.just(listOf(currencyEuro))
        every { _currencyDao.insertAll(any()) } returns Single.just(listOf())

        repository.getRates()
            .test()
            .assertValue(RatesTable(listOf(Currency("EUR", 1.0, 1, 1))))
    }

    @Test
    fun `should be empty on api error and empty database table`() {
        val expectedError = SocketException("No internet connection")
        val currencyEuro = CurrencyDb("EUR", 1.0, true)

        every {
            _currencyApi.getLatestRates()
        } returns Single.error(expectedError)
        every { _currencyDao.selectAll() } returns Single.just(listOf())
        every { _currencyDao.insertAll(any()) } returns Single.just(listOf())

        repository.getRates()
            .test()
            .assertValue(RatesTable(listOf()))
    }
}