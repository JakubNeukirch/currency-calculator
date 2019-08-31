package pl.jakubneukirch.currencycalculator.matchers

import android.view.View
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun hasTabCount(count: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("Check if TaLbLayout has $count tabs")
        }

        override fun matchesSafely(item: View?): Boolean {
            return item is TabLayout? && item?.tabCount == count
        }
    }
}

fun isTabAtSelected(index: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("Check if $index tab is displayed")
        }

        override fun matchesSafely(item: View?): Boolean {
            return item is TabLayout? && item?.selectedTabPosition == index
        }
    }
}