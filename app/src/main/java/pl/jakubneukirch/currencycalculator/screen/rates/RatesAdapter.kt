package pl.jakubneukirch.currencycalculator.screen.rates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_currency.view.*
import pl.jakubneukirch.currencycalculator.R
import pl.jakubneukirch.currencycalculator.data.model.view.Currency
import pl.jakubneukirch.currencycalculator.utils.removeZeros


class RatesAdapter : RecyclerView.Adapter<RatesAdapter.ViewHolder>() {

    var currencies: List<Currency> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_currency,
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = currencies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currencies[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(currency: Currency) {
            with(itemView) {
                currencyAbbreviationTextView.text = currency.abbreviation
                currencyNameTextView.setText(currency.nameId)
                rateEditText.setText("${currency.rate.removeZeros()}")
                Glide.with(itemView)
                    .load(currency.flagId)
                    .apply {
                        diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    }
                    .into(itemView.currencyFlagImageView)
            }
        }
    }
}