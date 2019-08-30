package pl.jakubneukirch.currencycalculator.utils

/**
 * Moves element from one index to another
 *
 * @param from  index of element which should be moved
 * @param to    index of position to which element should be moved
 */
fun <T> MutableList<T>.move(from: Int, to: Int) {
    val element = removeAt(from)
    add(to, element)
}