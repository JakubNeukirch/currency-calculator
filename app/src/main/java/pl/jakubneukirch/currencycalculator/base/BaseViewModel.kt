package pl.jakubneukirch.currencycalculator.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val disposables = CompositeDisposable()
    /**
     * all Disposables which are added to this CompositeDisposable must be invoked after onStart and before onStop
     * this CompositeDisposable is disposed in onStop
     */
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