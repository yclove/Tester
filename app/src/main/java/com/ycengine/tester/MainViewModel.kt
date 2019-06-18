package com.ycengine.tester

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

class MainViewModel : BaseViewModel() {

    val mutableLiveData: MutableLiveData<String> = MutableLiveData()
    val singleLiveEvent: SingleLiveEvent<String> = SingleLiveEvent()
    private val atomicBoolean = AtomicBoolean(false)

    init {
        mutableLiveData.value = "Mu"
        singleLiveEvent.value = "Si"
    }

    fun onClickDone(@Suppress("UNUSED_PARAMETER") view: View) {
        mutableLiveData.value = "4 Mu"
        singleLiveEvent.value = "4 Si"

        Toast.makeText(MainApplication.instance.applicationContext, mutableLiveData.value, Toast.LENGTH_SHORT).show()

        Timber.e("atomicBoolean = ${atomicBoolean.get()}")
        val result = atomicBoolean.compareAndSet(false, true)
        Timber.e("result = $result")
        Timber.e("atomicBoolean = ${atomicBoolean.get()}")
    }
}