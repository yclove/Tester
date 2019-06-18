package com.ycengine.tester

import android.view.View
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.ycengine.tester.vo.UserModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

data class Item(private var _name: String = "", private var _age: String = "") : BaseObservable() {

    var name: String
        @Bindable get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }

    var age: String
        @Bindable get() = _age
        set(value) {
            _age = value
            notifyPropertyChanged(BR.age)
        }
}

class MainViewModel : BaseViewModel() {

    private var count: Int = 0
    val observable = Item()
    val observableFieldSt = ObservableField<String>()
    val observableFieldNd = ObservableField<String>()

    val mutableLiveData: MutableLiveData<String> = MutableLiveData()
    val singleLiveEvent: SingleLiveEvent<String> = SingleLiveEvent()
    val userModel: MutableLiveData<UserModel> = MutableLiveData()

    init {
        mutableLiveData.value = "Mu"
        singleLiveEvent.value = "Si"

        Flowable.just(Any())
            .subscribeOn(Schedulers.io())
            .map {
                remoteRepository.getUserData()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userModel.value = it
            }, {
//                if (it is PostException) {
//                    postException.value = it
//                }
                it.printStackTrace()
            }).apply {
                compositeDisposable.add(this)
            }
    }

    fun onClickDone(@Suppress("UNUSED_PARAMETER") view: View) {
        observable.name = "${count++} YC"
        observable.age = "${count++} AGE"
        observableFieldSt.set("${count++} Hello")
        observableFieldNd.set("${count++} How are you")

        mutableLiveData.value = "${count++} Mu"
        singleLiveEvent.value = "${count++} Si"

        Toast.makeText(MainApplication.instance.applicationContext, "${ErrorApi.NOT_FOUND.code} :: ${ErrorApi.NOT_FOUND.message} :: ${ErrorApi.NOT_FOUND.ordinal} :: ${ErrorApi.NOT_FOUND.name}", Toast.LENGTH_SHORT).show()
    }
}