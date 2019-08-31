package pl.jakubneukirch.currencycalculator.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Currency")
data class CurrencyDb(
    @PrimaryKey
    val abbreviation: String,
    val rate: Double
)