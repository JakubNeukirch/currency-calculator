package pl.jakubneukirch.currencycalculator.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import pl.jakubneukirch.currencycalculator.app.App
import pl.jakubneukirch.currencycalculator.di.modules.AppModule
import pl.jakubneukirch.currencycalculator.di.modules.ConverterModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, ConverterModule::class]
)
interface AppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}