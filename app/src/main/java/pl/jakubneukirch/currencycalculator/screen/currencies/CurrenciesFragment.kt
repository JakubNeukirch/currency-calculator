package pl.jakubneukirch.currencycalculator.screen.currencies

import kotlinx.android.synthetic.main.fragment_currencies.*
import pl.jakubneukirch.currencycalculator.R
import pl.jakubneukirch.currencycalculator.base.BaseFragment
import pl.jakubneukirch.currencycalculator.base.BaseViewModel

abstract class CurrenciesFragment<T : BaseViewModel> :
    BaseFragment<T>(R.layout.fragment_currencies) {
    fun setEmptyPage(isEmpty: Boolean) {
        val nextChild = if (isEmpty) CHILD_EMPTY_PAGE else CHILD_RECYCLER_VIEW
        if (currenciesViewFlipper.displayedChild != nextChild) {
            currenciesViewFlipper.displayedChild = nextChild
        }

    }

    companion object {
        private const val CHILD_RECYCLER_VIEW = 0
        private const val CHILD_EMPTY_PAGE = 1
    }
}