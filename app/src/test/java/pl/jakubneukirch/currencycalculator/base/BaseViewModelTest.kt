package pl.jakubneukirch.currencycalculator.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import pl.jakubneukirch.currencycalculator.RxOverrideScheduleRule

@RunWith(MockitoJUnitRunner::class)
abstract class BaseViewModelTest<T : BaseViewModel> {
    @get:Rule
    val rxSchedulerRule = RxOverrideScheduleRule()
    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    protected open lateinit var viewModel: T
}