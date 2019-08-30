package pl.jakubneukirch.currencycalculator.screen.main

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pl.jakubneukirch.currencycalculator.R
import pl.jakubneukirch.currencycalculator.screen.converter.ConverterFragment
import pl.jakubneukirch.currencycalculator.screen.rates.RatesFragment

class MainPagerAdapter(fragmentManager: FragmentManager, private val resources: Resources) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            SCREEN_RATES -> RatesFragment()
            else -> ConverterFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            SCREEN_RATES -> resources.getString(R.string.main_all_rates)
            else -> resources.getString(R.string.main_converter)
        }
    }

    override fun getCount(): Int = ITEM_COUNT

    companion object {
        private const val ITEM_COUNT = 2

        private const val SCREEN_RATES = 0
        private const val SCREEN_CONVERTER = 1
    }
}