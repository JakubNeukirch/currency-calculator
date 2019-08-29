package pl.jakubneukirch.currencycalculator.app

import android.app.AppComponentFactory
import android.app.Application
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import pl.jakubneukirch.currencycalculator.BuildConfig
import pl.jakubneukirch.currencycalculator.di.AppComponent
import pl.jakubneukirch.currencycalculator.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

class App:Application(), HasSupportFragmentInjector {
    @Inject
    protected lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        appComponent.inject(this)
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
}