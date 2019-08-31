package pl.jakubneukirch.currencycalculator.base

import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class BaseRepositoryTest<REPO: Any> {
    protected abstract var repository: REPO
}