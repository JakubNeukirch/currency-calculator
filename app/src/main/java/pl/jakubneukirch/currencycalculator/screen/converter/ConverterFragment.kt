package pl.jakubneukirch.currencycalculator.screen.converter

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_currencies.*
import pl.jakubneukirch.currencycalculator.screen.currencies.CurrenciesFragment

class ConverterFragment : CurrenciesFragment<ConverterViewModel>() {

    private val _converterAdapter: ConverterAdapter by lazy { ConverterAdapter() }

    override fun setupScreen() {
        super.setupScreen()
        subscribeToData()
        setupConverterRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.listenToRatesChanges()
    }

    private fun subscribeToData() {
        viewModel.convertedCurrencies.observe(this, Observer { convertedCurrencies ->
            if (convertedCurrencies.isNotEmpty()) {
                setEmptyPage(false)
                _converterAdapter.setConvertedCurrencies(convertedCurrencies)
            } else {
                setEmptyPage(true)
            }
        })
    }

    private fun setupConverterRecyclerView() {
        _converterAdapter.onCurrencyChanged = { chosenCurrency ->
            viewModel.setSourceCurrency(chosenCurrency)
        }
        currenciesRecyclerView.itemAnimator = DefaultItemAnimator().apply {
            supportsChangeAnimations = false
        }
        currenciesRecyclerView.adapter = _converterAdapter
        currenciesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}