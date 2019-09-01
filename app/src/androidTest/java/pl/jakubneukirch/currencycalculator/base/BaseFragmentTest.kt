package pl.jakubneukirch.currencycalculator.base

import androidx.fragment.app.Fragment
import androidx.test.rule.ActivityTestRule
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Rule
import pl.jakubneukirch.currencycalculator.R
import pl.jakubneukirch.currencycalculator.screen.debug.DebugActivity
import kotlin.reflect.KClass

open class BaseFragmentTest<T : Fragment>(private val _fragmentClass: KClass<T>) {

    @get:Rule
    var activityRule = ActivityTestRule(DebugActivity::class.java)

    @Before
    fun setup() {
        activityRule.activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.debugFragmentFrame, _fragmentClass.constructors.first().call())
            .commit()
        RxJavaPlugins.setInitComputationSchedulerHandler(
            Rx2Idler.create("RxJava 2.x Computation Scheduler")
        )
        RxJavaPlugins.setInitIoSchedulerHandler(
            Rx2Idler.create("RxJava 2.x IO Scheduler")
        )
    }
}