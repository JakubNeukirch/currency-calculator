package pl.jakubneukirch.currencycalculator.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pl.jakubneukirch.currencycalculator.R

class MainActivity : AppCompatActivity() {

    private val _pagerAdapter: MainPagerAdapter by lazy {
        MainPagerAdapter(supportFragmentManager, resources)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()
        setupToolbar()
    }

    private fun setupViewPager() {
        mainViewPager.adapter = _pagerAdapter
        mainTabLayout.setupWithViewPager(mainViewPager)
    }


    private fun setupToolbar() {
        mainToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
