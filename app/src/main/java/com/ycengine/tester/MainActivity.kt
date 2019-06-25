package com.ycengine.tester

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ycengine.tester.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        
        initViewModel()

        Timber.e("master commit #1")

        Timber.e("master commit #2")

        Timber.e("rebase test : 01")
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders
            .of(this, object: ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return modelClass.getConstructor().newInstance()
                }
            })
            .get(MainViewModel::class.java)

        viewModel.mutableLiveData.observe(this, Observer {
            it?.let { data ->
                Timber.e("mutableLiveData = $data")
            }
        })

        viewModel.singleLiveEvent.observe(this, Observer {
            it?.let { data ->
                Timber.e("singleLiveEvent = $data")
            }
        })

        viewModel.userModel.observe(this, Observer {
            it?.let { data ->
                Timber.e("${data.CODE} :: ${data.MESSAGE} :: ${data.RESPONSE.UAI}")
            }
        })

        binding.viewModel = viewModel
    }
}
