package pl.jakubneukirch.currencycalculator.base

interface UseCase<PARAM, RESULT> {
    object None

    fun run(params: PARAM): RESULT

    operator fun invoke(params: PARAM): RESULT = run(params)
}