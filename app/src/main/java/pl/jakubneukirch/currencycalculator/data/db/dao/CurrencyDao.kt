package pl.jakubneukirch.currencycalculator.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import pl.jakubneukirch.currencycalculator.data.model.db.CurrencyDb

@Dao
interface CurrencyDao {
    @Insert
    fun insertAll(currencies: List<CurrencyDb>): Single<List<Int>>

    @Query("SELECT * FROM Currency")
    fun selectAll(): Single<List<CurrencyDb>>
}