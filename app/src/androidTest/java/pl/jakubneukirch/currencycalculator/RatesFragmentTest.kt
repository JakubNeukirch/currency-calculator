package pl.jakubneukirch.currencycalculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import kotlinx.android.synthetic.main.item_currency.view.*
import org.junit.Test
import pl.jakubneukirch.currencycalculator.base.BaseFragmentTest
import pl.jakubneukirch.currencycalculator.matchers.assertItemAt
import pl.jakubneukirch.currencycalculator.matchers.customActionOnItemAt
import pl.jakubneukirch.currencycalculator.screen.converter.ConverterAdapter
import pl.jakubneukirch.currencycalculator.screen.rates.RatesFragment

class RatesFragmentTest : BaseFragmentTest<RatesFragment>(RatesFragment::class) {

    @Test
    fun shouldEuroBeFirstByDefault() {
        Espresso.onView(ViewMatchers.withId(R.id.currenciesRecyclerView))
            .check(
                ViewAssertions.matches(
                    assertItemAt(0) {
                        currencyAbbreviationTextView.text.toString() == "EUR"
                    }
                )
            )
    }

    @Test
    fun shouldNotChangePositionOnClick() {
        var expectedAbbreviation = ""
        Espresso.onView(ViewMatchers.withId(R.id.currenciesRecyclerView))
            .perform(
                customActionOnItemAt(3) {
                    expectedAbbreviation = currencyAbbreviationTextView.text.toString()
                }
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ConverterAdapter.ViewHolder>(
                    3,
                    ViewActions.click()
                )
            )

        Espresso.onView(ViewMatchers.withId(R.id.currenciesRecyclerView))
            .check(
                ViewAssertions.matches(
                    assertItemAt(3) {
                        currencyAbbreviationTextView.text.toString() == expectedAbbreviation
                    }
                )
            )
    }
}