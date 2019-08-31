package pl.jakubneukirch.currencycalculator.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.jakubneukirch.currencycalculator.data.db.dao.CurrencyDao
import pl.jakubneukirch.currencycalculator.data.model.db.CurrencyDb

@Database(entities = [CurrencyDb::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}