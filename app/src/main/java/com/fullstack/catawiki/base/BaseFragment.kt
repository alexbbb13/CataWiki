package com.fullstack.catawiki.base

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.fullstack.catawiki.R
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment<VM : BaseViewModel, VB: ViewBinding> : Fragment() {

    protected abstract val viewModel: VM
    protected lateinit var binding: VB

    protected abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    protected abstract fun initView(view: View, savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = initBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view, savedInstanceState)
    }

    interface FragmentStateListener{
        fun onFragmentStateChanged(state: Int)
    }

    open fun onServerError(t: Throwable) {
        t.printStackTrace()
        Toast.makeText(context, t.message, Toast.LENGTH_LONG)
    }

    open fun onNetworkError(coordinatorLayout: CoordinatorLayout) {
        Snackbar.make(
            coordinatorLayout,
            R.string.network_not_available,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
