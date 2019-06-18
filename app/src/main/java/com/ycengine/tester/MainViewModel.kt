package com.ycengine.tester

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

class MainViewModel : BaseViewModel() {

    val mutableLiveData: MutableLiveData<String> = MutableLiveData()
    val singleLiveEvent: SingleLiveEvent<String> = SingleLiveEvent()

    init {
        mutableLiveData.value = "Mu"
        singleLiveEvent.value = "Si"
    }

    fun onClickDone(@Suppress("UNUSED_PARAMETER") view: View) {
        mutableLiveData.value = "4 Mu"
        singleLiveEvent.value = "4 Si"

        Toast.makeText(MainApplication.instance.applicationContext, mutableLiveData.value, Toast.LENGTH_SHORT).show()
    }
}