package pl.jakubneukirch.currencycalculator.data.model.view

data class Currency(
    val currencyAbbreviation: String,
    val rate: Double,
    val currencyName: String = "US Dollars", //todo set real name
    val currencyFlag: String = ""
) {
    companion object {
        private const val BASE_VALUE = 1.0

        fun baseRate(name: String): Currency {
            return Currency(
                currencyAbbreviation = name,
                rate = BASE_VALUE
            )
        }
    }
}