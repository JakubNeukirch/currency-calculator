package pl.jakubneukirch.currencycalculator.base

import androidx.fragment.app.Fragment
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import pl.jakubneukirch.currencycalculator.R
import pl.jakubneukirch.currencycalculator.screen.debug.DebugActivity
import kotlin.reflect.KClass

open class BaseFragmentTest<T : Fragment>(private val _fragmentClass: KClass<T>) {

    @get:Rule
    var _activityRule = ActivityTestRule(DebugActivity::class.java)

    @Before
    fun setup() {
        _activityRule.activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.debugFragmentFrame, _fragmentClass.constructors.first().call())
            .commit()
    }
}