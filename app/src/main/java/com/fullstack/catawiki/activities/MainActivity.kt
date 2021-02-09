package com.fullstack.catawiki.activities

import android.os.Bundle
import android.widget.FrameLayout
import com.fullstack.catawiki.R
import com.fullstack.catawiki.base.BaseFragment
import com.fullstack.catawiki.fragments.CatsGridFragment

import dagger.android.support.DaggerAppCompatActivity


class MainActivity : DaggerAppCompatActivity() {

    private lateinit var frameLayout: FrameLayout
    var currentState: Int = BaseFragment.FRAGMENT_CAT_GRID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        frameLayout = findViewById(R.id.fragment_container)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, CatsGridFragment())
            .addToBackStack("cat_giod")
            .commit()
    }

    fun notifyFragmentStateSelected(fragmentState: Int){
        supportFragmentManager.fragments.filter {
            it is BaseFragment.FragmentStateListener
        }
            .forEach { fragment->
                (fragment as BaseFragment.FragmentStateListener).onFragmentStateChanged(fragmentState)
            }
    }
}
