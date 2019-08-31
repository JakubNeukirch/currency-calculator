package pl.jakubneukirch.currencycalculator.screen.rates

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_currencies.*
import pl.jakubneukirch.currencycalculator.screen.currencies.CurrenciesFragment

class RatesFragment : CurrenciesFragment<RatesViewModel>() {

    private val _ratesAdapter: RatesAdapter by lazy { RatesAdapter() }

    override fun setupScreen() {
        subscribeToData()
        setupRatesRecyclerView()
    }

    private fun subscribeToData() {
        viewModel.ratesTable.observe(this, Observer { ratesTable ->
            setEmptyPage(ratesTable.currencies.isEmpty())
            _ratesAdapter.currencies = ratesTable.currencies
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