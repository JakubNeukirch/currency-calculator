package pl.jakubneukirch.currencycalculator.utils.android

import androidx.viewpager.widget.ViewPager

class PageChangedListener(private val _onPageChanged: OnPageChanged) :
    ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) = Unit

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) =
        Unit

    override fun onPageSelected(position: Int) {
        _onPageChanged(position)
    }
}

typealias OnPageChanged = (position: Int) -> Unit