package pl.jakubneukirch.currencycalculator.screen.rates

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_currencies.*
import pl.jakubneukirch.currencycalculator.R
import pl.jakubneukirch.currencycalculator.base.BaseFragment

class RatesFragment : BaseFragment<RatesViewModel>(R.layout.fragment_currencies) {

    private val _ratesAdapter: RatesAdapter by lazy { RatesAdapter() }

    override fun setupScreen() {
        subscribeToData()
        setupRatesRecyclerView()
    }

    private fun subscribeToData() {
        viewModel.ratesTable.observe(this, Observer { ratesTable ->
            _ratesAdapter.currencies = ratesTable.allCurrencies
        })
    }

    private fun setupRatesRecyclerView() {
        currenciesRecyclerView.adapter = _ratesAdapter
        currenciesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onStart() {
        super.onStart()
        viewModel.listenToRatesChanges()
    }
}