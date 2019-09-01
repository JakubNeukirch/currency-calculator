package pl.jakubneukirch.currencycalculator.matchers

import android.view.View
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun assertItemAt(index: Int, assertion: View.() -> Boolean): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("Check if custom assertion passes on item at $index")
        }

        override fun matchesSafely(item: View?): Boolean {
            return if (item is RecyclerView? && item != null) {
                item[index].assertion()
            } else {
                false
            }
        }
    }
}

fun customActionOnItemAt(index: Int, action: View.() -> Unit): ViewAction {
    return object : ViewAction {
        override fun getDescription(): String {
            return "Get item at $index"
        }

        override fun getConstraints(): Matcher<View> {
            return instanceOf(RecyclerView::class.java)
        }

        override fun perform(uiController: UiController?, view: View?) {
            if (view is RecyclerView? && view != null) {
                view[index].action()
            }
        }
    }
}