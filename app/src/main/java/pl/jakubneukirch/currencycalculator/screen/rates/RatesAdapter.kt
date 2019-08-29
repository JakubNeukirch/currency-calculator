package pl.jakubneukirch.currencycalculator.screen.rates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rate.view.*
import pl.jakubneukirch.currencycalculator.R
import pl.jakubneukirch.currencycalculator.data.model.view.Rate


class RatesAdapter : RecyclerView.Adapter<RatesAdapter.ViewHolder>() {

    var rates: List<Rate> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_rate,
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = rates.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rates[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(rate: Rate) {
            with(itemView) {
                currencyAbbreviationTextView.text = rate.currency
                currencyNameTextView.text = "US Dollars" 
                rateTextView.text = "${rate.value}"
            }
        }
    }
}