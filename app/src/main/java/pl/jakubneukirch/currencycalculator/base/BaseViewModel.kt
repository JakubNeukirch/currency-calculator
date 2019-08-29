package pl.jakubneukirch.currencycalculator.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val disposables = CompositeDisposable()
    protected lateinit var onStopDisposables: CompositeDisposable
        private set

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun onStart() {
        onStopDisposables = CompositeDisposable()
    }

    fun onStop() {
        onStopDisposables.dispose()
    }

}