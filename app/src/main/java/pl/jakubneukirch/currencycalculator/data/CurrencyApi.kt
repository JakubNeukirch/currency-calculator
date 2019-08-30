package pl.jakubneukirch.currencycalculator.data

import io.reactivex.Single
import pl.jakubneukirch.currencycalculator.data.model.api.RatesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    /**
     * Gets latest rates of currencies.
     * @param base  defines abbreviation on which values will be based
     */
    @GET("latest")
    fun getLatestRates(@Query("base") base: String = DEFAULT_CURRENCY): Single<RatesResponse>

    companion object {
        const val BASE_URL = "https://revolut.duckdns.org/"
        const val DEFAULT_CURRENCY = "EUR"
    }
}