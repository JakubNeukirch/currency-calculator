package pl.jakubneukirch.currencycalculator.data.model.view

data class Rate(
    val currencyAbbreviation: String,
    val value: Double,
    val currencyName: String = "US Dollars", //todo set real name
    val currencyFlag: String = ""
) {
    companion object {
        private const val BASE_VALUE = 1.0

        fun baseRate(name: String): Rate {
            return Rate(
                currencyAbbreviation = name,
                value = BASE_VALUE
            )
        }
    }
}