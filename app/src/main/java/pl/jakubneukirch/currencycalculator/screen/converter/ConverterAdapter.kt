package pl.jakubneukirch.currencycalculator.screen.converter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_currency.view.*
import pl.jakubneukirch.currencycalculator.R
import pl.jakubneukirch.currencycalculator.data.model.view.ConvertedCurrency
import pl.jakubneukirch.currencycalculator.utils.android.TextChangedListener
import pl.jakubneukirch.currencycalculator.utils.android.loadResource
import pl.jakubneukirch.currencycalculator.utils.removeZeros

class ConverterAdapter : RecyclerView.Adapter<ConverterAdapter.ViewHolder>() {

    private var _convertedCurrencies: List<ConvertedCurrency> = listOf()

    var onCurrencyChanged: (convertedCurrency: ConvertedCurrency) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        )
    }

    override fun getItemCount(): Int = _convertedCurrencies.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = _convertedCurrencies[position]
        holder.bind(item)
    }

    fun setConvertedCurrencies(convertedCurrencies: List<ConvertedCurrency>) {
        val oldList = _convertedCurrencies
        _convertedCurrencies = convertedCurrencies
        DiffUtil.calculateDiff(ConverterDiffUtil(oldList, _convertedCurrencies), true)
            .dispatchUpdatesTo(this)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.rateEditText.isEnabled = true
            setupListeners()
        }

        fun bind(convertedCurrency: ConvertedCurrency) {
            with(itemView) {
                currencyAbbreviationTextView.text = convertedCurrency.currency.abbreviation
                currencyNameTextView.setText(convertedCurrency.currency.nameId)
                rateEditText.setText(convertedCurrency.value.validateRateValueText())
                itemView.currencyFlagImageView.loadResource(convertedCurrency.currency.flagId)
            }
        }

        private fun Double.validateRateValueText(): String {
            return if (this == 0.0) {
                ""
            } else {
                this.removeZeros().toString()
            }

        }

        private fun setupListeners() {
            setupItemClickListener()
            setupRateChangedListener()
        }

        private fun setupRateChangedListener() {
            itemView.rateEditText.addTextChangedListener(TextChangedListener { text ->
                if (itemView.rateEditText.isFocused) {
                    val value = text.toDoubleOrNull() ?: 0.0
                    _convertedCurrencies[adapterPosition].value = value
                    onCurrencyChanged(_convertedCurrencies[adapterPosition])
                }
            })
        }

        private fun setupItemClickListener() {
            itemView.setOnClickListener { view ->
                if (!itemView.rateEditText.isFocused) {
                    onCurrencyChanged(_convertedCurrencies[adapterPosition])
                    view.requestFocus()
                }
            }
        }
    }
}