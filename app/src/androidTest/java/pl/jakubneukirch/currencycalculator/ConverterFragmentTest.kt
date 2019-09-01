package pl.jakubneukirch.currencycalculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import pl.jakubneukirch.currencycalculator.base.BaseFragmentTest
import pl.jakubneukirch.currencycalculator.screen.converter.ConverterFragment

@RunWith(AndroidJUnit4::class)
class ConverterFragmentTest : BaseFragmentTest<ConverterFragment>(ConverterFragment::class) {

    @Test
    fun shouldTest() {
        onView(withId(R.id.currenciesRecyclerView))
            .check(matches(isDisplayed()))
    }
}