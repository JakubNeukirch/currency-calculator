package pl.jakubneukirch.currencycalculator.usecase

import org.junit.Test
import pl.jakubneukirch.currencycalculator.base.BaseUseCaseTest
import pl.jakubneukirch.currencycalculator.data.model.view.ConvertedCurrency
import pl.jakubneukirch.currencycalculator.data.model.view.Currency
import pl.jakubneukirch.currencycalculator.data.model.view.RatesTable

class ConvertValuesTest : BaseUseCaseTest<IConvertValues>() {
    override lateinit var useCase: IConvertValues

    override fun setup() {
        super.setup()
        useCase = ConvertValues()
    }

    @Test
    fun `should calculate currency values`() {
        val currencyPln = Currency("PLN", 4.31)
        val currencyAud = Currency("AUD", 1.61)
        val currencyEur = Currency.baseRate("EUR")
        val testRates = RatesTable(
            baseCurrency = Currency.baseRate("EUR"),
            currencies = listOf(
                currencyPln,
                currencyAud
            )
        )
        val sourceCurrency = ConvertedCurrency(currencyPln, 7.33)

        val expectedCurrencies = listOf(
            ConvertedCurrency(currencyPln, 7.33),
            ConvertedCurrency(currencyEur, 1.70),
            ConvertedCurrency(currencyAud, 2.74)
        )

        useCase(
            IConvertValues.Params(
                sourceCurrency = sourceCurrency,
                rates = testRates
            )
        )
            .test()
            .assertValue(expectedCurrencies)
    }
}