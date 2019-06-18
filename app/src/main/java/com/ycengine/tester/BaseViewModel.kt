package com.ycengine.tester

import androidx.lifecycle.ViewModel
import com.ycengine.tester.api.RemoteRepository
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val remoteRepository by lazy {
        RemoteRepository()
    }

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}