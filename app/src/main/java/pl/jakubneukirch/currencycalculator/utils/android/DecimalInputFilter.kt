package pl.jakubneukirch.currencycalculator.utils.android

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Matcher
import java.util.regex.Pattern

class DecimalInputFilter: InputFilter {

    private val _pattern: Pattern = Pattern.compile("^\\d+\\.?\\d{0,2}\$")

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        if(!_pattern.matcher(dest).matches()){
            return ""
        }
        return null
    }
}