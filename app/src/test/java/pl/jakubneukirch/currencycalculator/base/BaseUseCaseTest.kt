package pl.jakubneukirch.currencycalculator.base

import io.reactivex.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class BaseUseCaseTest<UC : UseCase<*, *>> {
    protected open lateinit var useCase: UC

    @Before
    open fun setup() {
        RxJavaPlugins.reset()
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
    }
}
