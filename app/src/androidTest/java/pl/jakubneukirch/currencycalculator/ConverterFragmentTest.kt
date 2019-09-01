package pl.jakubneukirch.currencycalculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.android.synthetic.main.item_currency.view.*
import org.junit.Test
import org.junit.runner.RunWith
import pl.jakubneukirch.currencycalculator.base.BaseFragmentTest
import pl.jakubneukirch.currencycalculator.matchers.assertItemAt
import pl.jakubneukirch.currencycalculator.matchers.customActionOnItemAt
import pl.jakubneukirch.currencycalculator.screen.converter.ConverterAdapter
import pl.jakubneukirch.currencycalculator.screen.converter.ConverterFragment

@RunWith(AndroidJUnit4::class)
class ConverterFragmentTest : BaseFragmentTest<ConverterFragment>(ConverterFragment::class) {

    @Test
    fun shouldEuroBeFirstByDefault() {
        onView(withId(R.id.currenciesRecyclerView))
            .check(
                matches(
                    assertItemAt(0) {
                        currencyAbbreviationTextView.text.toString() == "EUR"
                    }
                )
            )
    }

    @Test
    fun shouldChangePositionOnClick() {
        var expectedAbbreviation = ""
        onView(withId(R.id.currenciesRecyclerView))
            .perform(
                customActionOnItemAt(3) {
                    expectedAbbreviation = currencyAbbreviationTextView.text.toString()
                }
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ConverterAdapter.ViewHolder>(3, click())
            )

        onView(withId(R.id.currenciesRecyclerView))
            .check(
                matches(
                    assertItemAt(0) {
                        currencyAbbreviationTextView.text.toString() == expectedAbbreviation
                    }
                )
            )
    }
}