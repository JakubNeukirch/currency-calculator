package pl.jakubneukirch.currencycalculator.screen.converter

import androidx.recyclerview.widget.DiffUtil
import pl.jakubneukirch.currencycalculator.data.model.view.ConvertedCurrency

class ConverterDiffUtil(
    private val _oldList: List<ConvertedCurrency>,
    private val _newList: List<ConvertedCurrency>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = _oldList.size

    override fun getNewListSize(): Int = _newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCurrency = _oldList[oldItemPosition].currency
        val newCurrency = _newList[newItemPosition].currency
        return oldCurrency.abbreviation == newCurrency.abbreviation
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCurrency = _oldList[oldItemPosition]
        val newCurrency = _newList[newItemPosition]
        return oldCurrency == newCurrency
    }
}