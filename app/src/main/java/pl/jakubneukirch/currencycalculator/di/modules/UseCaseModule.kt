package pl.jakubneukirch.currencycalculator.di.modules

import dagger.Module
import dagger.Provides
import pl.jakubneukirch.currencycalculator.data.repository.CurrencyRepository
import pl.jakubneukirch.currencycalculator.usecase.ConvertValues
import pl.jakubneukirch.currencycalculator.usecase.GetRatesUpdates
import pl.jakubneukirch.currencycalculator.usecase.IConvertValues
import pl.jakubneukirch.currencycalculator.usecase.IGetRatesUpdates
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetRatesUpdates(currencyRepository: CurrencyRepository): IGetRatesUpdates {
        return GetRatesUpdates(currencyRepository)
    }

    @Provides
    @Singleton
    fun provideConverValues() : IConvertValues {
        return ConvertValues()
    }
}