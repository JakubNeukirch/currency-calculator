package pl.jakubneukirch.currencycalculator.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pl.jakubneukirch.currencycalculator.R
import pl.jakubneukirch.currencycalculator.screen.converter.ConverterFragment
import pl.jakubneukirch.currencycalculator.screen.rates.RatesFragment

class MainActivity : AppCompatActivity() {

    private val _pagerAdapter: MainPagerAdapter by lazy {
        MainPagerAdapter(supportFragmentManager, resources)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()
    }

    private fun setupViewPager() {
        mainViewPager.adapter = _pagerAdapter
        mainTabLayout.setupWithViewPager(mainViewPager)
    }
}
