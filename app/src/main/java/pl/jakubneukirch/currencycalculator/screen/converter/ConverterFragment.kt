package pl.jakubneukirch.currencycalculator.screen.converter

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_converter.*
import pl.jakubneukirch.currencycalculator.R
import pl.jakubneukirch.currencycalculator.base.BaseFragment
import timber.log.Timber

class ConverterFragment : BaseFragment<ConverterViewModel>(R.layout.fragment_converter) {

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
            _converterAdapter.setConvertedCurrencies(convertedCurrencies)
        })
    }

    private fun setupConverterRecyclerView() {
        _converterAdapter.onCurrencyChanged = { chosenCurrency ->
            viewModel.setSourceCurrency(chosenCurrency)
        }
        converterRecyclerView.adapter = _converterAdapter
        converterRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}