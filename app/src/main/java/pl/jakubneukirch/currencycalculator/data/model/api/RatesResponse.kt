package pl.jakubneukirch.currencycalculator.data.model.api

import com.google.gson.annotations.SerializedName
import java.util.*

data class RatesResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: Date,
    @SerializedName("rates")
    val rates: Map<String, Double>
)