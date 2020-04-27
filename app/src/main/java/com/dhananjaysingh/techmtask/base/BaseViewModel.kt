package com.dhananjaysingh.techmtask.base

import androidx.lifecycle.ViewModel
import com.dhananjaysingh.techmtask.injection.ViewModelInjector
import com.dhananjaysingh.techmtask.injection.DaggerViewModelInjector
import com.dhananjaysingh.techmtask.network.NetworkModule
import com.dhananjaysingh.techmtask.viewmodel.MainActivityViewModel

abstract class BaseViewModel:ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainActivityViewModel -> injector.inject(this)
        }
    }
}