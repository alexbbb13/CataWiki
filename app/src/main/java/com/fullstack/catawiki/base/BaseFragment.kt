package com.fullstack.catawiki.base

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import javax.inject.Inject

open class BaseFragment : MvpAppCompatFragment() {

//    @Inject
//    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>
//
//    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
//        return childFragmentInjector
//    }

    interface FragmentStateListener{
        fun onFragmentStateChanged(state: Int)
    }

    companion object {
        const val FRAGMENT_CAT_GRID = 0
    }
}
