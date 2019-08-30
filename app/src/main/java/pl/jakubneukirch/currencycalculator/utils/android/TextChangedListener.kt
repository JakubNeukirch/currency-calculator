package pl.jakubneukirch.currencycalculator.utils.android

import android.text.Editable
import android.text.TextWatcher

/**
 * Wrapper class for TextWatcher to simplify watching EditTexts text changes
 */
class TextChangedListener(private val _onTextChanged: OnTextChanged) : TextWatcher {
    override fun afterTextChanged(s: Editable?) = Unit
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        _onTextChanged(s?.toString() ?: "")
    }
}

typealias OnTextChanged = (text: String) -> Unit