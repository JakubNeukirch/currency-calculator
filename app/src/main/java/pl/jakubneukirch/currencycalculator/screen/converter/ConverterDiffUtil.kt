package pl.jakubneukirch.currencycalculator.screen.converter

import androidx.recyclerview.widget.DiffUtil
import pl.jakubneukirch.currencycalculator.data.model.view.ConvertedCurrency
import timber.log.Timber

class ConverterDiffUtil(
    private val _oldList: List<ConvertedCurrency>,
    private val _newList: List<ConvertedCurrency>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCurrency = _oldList[oldItemPosition].currency
        val newCurrency = _newList[newItemPosition].currency
        if(oldCurrency.abbreviation == "EUR") {
            Timber.i("old ${_oldList[oldItemPosition]}")
            Timber.i("new ${_newList[newItemPosition]}")
        }
        return oldCurrency.abbreviation == newCurrency.abbreviation
    }

    override fun getOldListSize(): Int = _oldList.size

    override fun getNewListSize(): Int = _newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCurrency = _oldList[oldItemPosition]
        val newCurrency = _newList[newItemPosition]
        return oldCurrency == newCurrency
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}