package pl.jakubneukirch.currencycalculator.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import pl.jakubneukirch.currencycalculator.base.ViewModelKey
import pl.jakubneukirch.currencycalculator.screen.converter.ConverterFragment
import pl.jakubneukirch.currencycalculator.screen.converter.ConverterViewModel
import pl.jakubneukirch.currencycalculator.usecase.IConvertValues
import pl.jakubneukirch.currencycalculator.usecase.IGetRatesUpdates

@Module(includes = [ConverterModule.ProvideViewModel::class])
abstract class ConverterModule {

    @ContributesAndroidInjector(
        modules = [InjectViewModel::class]
    )
    abstract fun bind(): ConverterFragment

    @Module
    class InjectViewModel {
        @Provides
        fun provideConverterViewModel(
            factory: ViewModelProvider.Factory,
            fragment: ConverterFragment
        ): ConverterViewModel {
            return ViewModelProviders.of(fragment, factory).get(ConverterViewModel::class.java)
        }
    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(ConverterViewModel::class)
        fun provideConverterViewModel(
            getRatesUpdates: IGetRatesUpdates,
            convertValues: IConvertValues
        ): ViewModel {
            return ConverterViewModel(getRatesUpdates, convertValues)
        }
    }
}