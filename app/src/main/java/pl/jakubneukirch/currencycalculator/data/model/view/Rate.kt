package pl.jakubneukirch.currencycalculator.data.model.view

data class Rate(
    val currency: String,
    val value: Double
){
    companion object {
        private const val BASE_VALUE = 1.0

        fun baseRate(name: String): Rate {
            return Rate(
                currency = name,
                value = BASE_VALUE
            )
        }
    }
}