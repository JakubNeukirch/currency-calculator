package pl.jakubneukirch.currencycalculator.data.model.view

data class Currency(
    val abbreviation: String,
    val rate: Double,
    val name: String = "US Dollars", //todo set real name
    val flagUrl: String = ""
) {
    companion object {
        private const val BASE_VALUE = 1.0

        fun baseRate(name: String): Currency {
            return Currency(
                abbreviation = name,
                rate = BASE_VALUE
            )
        }
    }
}