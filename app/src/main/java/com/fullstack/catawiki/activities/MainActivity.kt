package com.fullstack.catawiki.activities

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.fullstack.catawiki.R
import com.fullstack.catawiki.base.BaseFragment
import com.fullstack.catawiki.fragments.CatsGridFragment

import android.R.attr.data
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint()
class MainActivity : AppCompatActivity() {

    private lateinit var frameLayout: FrameLayout
    var shouldExit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        frameLayout = findViewById(R.id.fragment_container)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, CatsGridFragment())
            .addToBackStack("cat_grid")
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


    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        //Single-Activity App
        if (count == 1) {
            if(shouldExit) {
                finish()
            } else {
            Toast.makeText(
                this, "Press once again to exit",
                Toast.LENGTH_SHORT).show()
                shouldExit = true
                Handler(Looper.getMainLooper()).postDelayed({ shouldExit = false}, 3000)
            }
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
