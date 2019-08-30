package pl.jakubneukirch.currencycalculator.screen.converter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_currency.view.*
import pl.jakubneukirch.currencycalculator.R
import pl.jakubneukirch.currencycalculator.data.model.view.ConvertedCurrency
import pl.jakubneukirch.currencycalculator.utils.move

class ConverterAdapter : RecyclerView.Adapter<ConverterAdapter.ViewHolder>() {

    var convertedCurrencies: MutableList<ConvertedCurrency> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCurrencyChosen: (convertedCurrency: ConvertedCurrency) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        )
    }

    override fun getItemCount(): Int = convertedCurrencies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(convertedCurrencies[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.rateEditText.isEnabled = true
            itemView.setOnClickListener {
                moveToFirstPosition()
                onCurrencyChosen(convertedCurrencies[adapterPosition])
            }
        }

        fun bind(convertedCurrency: ConvertedCurrency) {
            with(itemView) {
                currencyAbbreviationTextView.text = convertedCurrency.currency.abbreviation
                currencyNameTextView.text = convertedCurrency.currency.name
                rateEditText.setText("${convertedCurrency.value}")
            }
        }

        private fun moveToFirstPosition() {
            if (layoutPosition != 0) {
                convertedCurrencies.move(layoutPosition, 0)
            }
            notifyItemMoved(layoutPosition, 0)
        }
    }
}