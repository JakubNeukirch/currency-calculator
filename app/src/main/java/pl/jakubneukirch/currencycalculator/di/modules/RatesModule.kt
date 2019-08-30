package pl.jakubneukirch.currencycalculator.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import pl.jakubneukirch.currencycalculator.base.ViewModelKey
import pl.jakubneukirch.currencycalculator.screen.rates.RatesFragment
import pl.jakubneukirch.currencycalculator.screen.rates.RatesViewModel
import pl.jakubneukirch.currencycalculator.screen.usecase.GetRatesUpdates
import pl.jakubneukirch.currencycalculator.screen.usecase.IGetRatesUpdates

@Module(includes = [RatesModule.ProvideViewModel::class])
abstract class RatesModule {

    @ContributesAndroidInjector(
        modules = [InjectViewModel::class]
    )
    abstract fun bind(): RatesFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideRatesViewModel(
            factory: ViewModelProvider.Factory,
            fragment: RatesFragment
        ): RatesViewModel {
            return ViewModelProviders.of(fragment, factory).get(RatesViewModel::class.java)
        }
    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(RatesViewModel::class)
        fun provideRatesViewModel(getRatesUpdates: IGetRatesUpdates): ViewModel {
            return RatesViewModel(getRatesUpdates)
        }
    }
}