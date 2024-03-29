package pl.jakubneukirch.currencycalculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pl.jakubneukirch.currencycalculator.matchers.hasTabCount
import pl.jakubneukirch.currencycalculator.matchers.hasTabTitlesInOrder
import pl.jakubneukirch.currencycalculator.matchers.isTabAtSelected
import pl.jakubneukirch.currencycalculator.screen.main.MainActivity
import pl.jakubneukirch.currencycalculator.screen.rates.RatesFragment

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun shouldHaveTwoTabs() {
        onView(withId(R.id.mainTabLayout))
            .check(matches(hasTabCount(2)))
    }

    @Test
    fun shouldSelectSecondTabOnSwipeLeft() {
        onView(withId(R.id.mainViewPager))
            .perform(swipeLeft())

        onView(withId(R.id.mainTabLayout))
            .check(matches(isTabAtSelected(1)))
    }

    @Test
    fun shouldDisplayRatesOnStart() {
        val ratesFragment = activityRule
            .activity
            .supportFragmentManager
            .fragments
            .find { it is RatesFragment }
        assertEquals(true, ratesFragment?.userVisibleHint)
    }

    @Test
    fun shouldHasCorrectTabTitles() {
        onView(withId(R.id.mainTabLayout))
            .check(
                matches(
                    hasTabTitlesInOrder(
                        listOf(
                            activityRule.activity.getString(R.string.main_all_rates),
                            activityRule.activity.getString(R.string.main_converter)
                        )
                    )
                )
            )
    }
}
