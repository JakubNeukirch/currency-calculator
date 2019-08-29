package pl.jakubneukirch.currencycalculator.base

import io.reactivex.Single

interface UseCase<PARAM, RESULT> {

    object None

    fun run(params: PARAM): Single<RESULT>

    operator fun invoke(params: PARAM): Single<RESULT> = run(params)
}