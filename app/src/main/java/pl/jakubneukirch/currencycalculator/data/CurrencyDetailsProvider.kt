package pl.jakubneukirch.currencycalculator.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import pl.jakubneukirch.currencycalculator.R
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Provides flags and full names of currencies
 */
@Singleton
class CurrencyDetailsProvider @Inject constructor() {


    fun getCurrencyDetailsFor(abbreviation: String): CurrencyDetails {
        return map[abbreviation] ?: CurrencyDetails(
            R.drawable.earth,
            R.string.currency_name_unknown
        )
    }

    data class CurrencyDetails(
        @DrawableRes
        val flagId: Int,
        @StringRes
        val nameId: Int
    )

    companion object {
        /**
         * Map of CurrencyDetails where key isl abbreviation and value isl CurrencyDetails object
         */
        private val map: Map<String, CurrencyDetails> = mapOf(
            "AUD" to CurrencyDetails(R.drawable.au, R.string.currency_name_aud),
            "BGN" to CurrencyDetails(R.drawable.bg, R.string.currency_name_bgn),
            "BRL" to CurrencyDetails(R.drawable.br, R.string.currency_name_brl),
            "CAD" to CurrencyDetails(R.drawable.ca, R.string.currency_name_cad),
            "CHF" to CurrencyDetails(R.drawable.ch, R.string.currency_name_chf),
            "CNY" to CurrencyDetails(R.drawable.cn, R.string.currency_name_cny),
            "CZK" to CurrencyDetails(R.drawable.cz, R.string.currency_name_czk),
            "DKK" to CurrencyDetails(R.drawable.dk, R.string.currency_name_dkk),
            "EUR" to CurrencyDetails(R.drawable.eu, R.string.currency_name_eur),
            "GBP" to CurrencyDetails(R.drawable.gb, R.string.currency_name_gbp),
            "HKD" to CurrencyDetails(R.drawable.hk, R.string.currency_name_hkd),
            "HRK" to CurrencyDetails(R.drawable.hr, R.string.currency_name_hrk),
            "HUF" to CurrencyDetails(R.drawable.hu, R.string.currency_name_huf),
            "IDR" to CurrencyDetails(R.drawable.id, R.string.currency_name_idr),
            "ILS" to CurrencyDetails(R.drawable.il, R.string.currency_name_ils),
            "INR" to CurrencyDetails(R.drawable.ind, R.string.currency_name_inr),
            "ISK" to CurrencyDetails(R.drawable.isl, R.string.currency_name_isk),
            "JPY" to CurrencyDetails(R.drawable.jp, R.string.currency_name_jpy),
            "KRW" to CurrencyDetails(R.drawable.kr, R.string.currency_name_krw),
            "MXN" to CurrencyDetails(R.drawable.mx, R.string.currency_name_mxn),
            "MYR" to CurrencyDetails(R.drawable.my, R.string.currency_name_myr),
            "NOK" to CurrencyDetails(R.drawable.no, R.string.currency_name_nok),
            "NZD" to CurrencyDetails(R.drawable.nz, R.string.currency_name_nzd),
            "PHP" to CurrencyDetails(R.drawable.ph, R.string.currency_name_php),
            "PLN" to CurrencyDetails(R.drawable.pl, R.string.currency_name_pln),
            "RON" to CurrencyDetails(R.drawable.ro, R.string.currency_name_ron),
            "RUB" to CurrencyDetails(R.drawable.ru, R.string.currency_name_rub),
            "SEK" to CurrencyDetails(R.drawable.se, R.string.currency_name_sek),
            "SGD" to CurrencyDetails(R.drawable.sg, R.string.currency_name_sgd),
            "THB" to CurrencyDetails(R.drawable.th, R.string.currency_name_thb),
            "TRY" to CurrencyDetails(R.drawable.tr, R.string.currency_name_try),
            "USD" to CurrencyDetails(R.drawable.us, R.string.currency_name_usd),
            "ZAR" to CurrencyDetails(R.drawable.za, R.string.currency_name_zar)
        )
    }
}