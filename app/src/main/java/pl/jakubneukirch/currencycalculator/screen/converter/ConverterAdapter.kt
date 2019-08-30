package pl.jakubneukirch.currencycalculator.screen.converter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_currency.view.*
import pl.jakubneukirch.currencycalculator.R
import pl.jakubneukirch.currencycalculator.data.model.view.ConvertedCurrency

class ConverterAdapter : RecyclerView.Adapter<ConverterAdapter.ViewHolder>() {

    private var _convertedCurrencies: List<ConvertedCurrency> = listOf()

    var onCurrencyChosen: (convertedCurrency: ConvertedCurrency) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        )
    }

    override fun getItemCount(): Int = _convertedCurrencies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_convertedCurrencies[position])
    }

     fun setConvertedCurrencies(convertedCurrencies: List<ConvertedCurrency>) {
        val oldList = _convertedCurrencies
        _convertedCurrencies = convertedCurrencies
        DiffUtil.calculateDiff(ConverterDiffUtil(oldList,_convertedCurrencies), true)
            .dispatchUpdatesTo(this)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.rateEditText.isEnabled = true
            itemView.setOnClickListener { view ->
                onCurrencyChosen(_convertedCurrencies[adapterPosition])
            }
        }

        fun bind(convertedCurrency: ConvertedCurrency) {
            with(itemView) {
                currencyAbbreviationTextView.text = convertedCurrency.currency.abbreviation
                currencyNameTextView.text = convertedCurrency.currency.name
                rateEditText.setText("${convertedCurrency.value}")
            }
        }
    }
}