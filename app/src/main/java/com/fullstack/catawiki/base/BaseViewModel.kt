package com.fullstack.catawiki.base

import android.os.Bundle
import android.view.View
import androidx.databinding.BaseObservable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel :  ViewModel(), CoroutineScope {

    private var job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    protected val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

    abstract fun onViewCreated(view: View, savedInstanceState: Bundle?)

//    fun onDefContext(block: () -> Unit) = launch {
//        withContext(Dispatchers.IO) {
//            block.invoke()
//        }
//    }
}