package com.fullstack.catawiki.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.fullstack.catawiki.R
import com.fullstack.catawiki.api.ResultWrapper
import com.fullstack.catawiki.base.BaseFragment
import com.fullstack.catawiki.databinding.CatInfoFragmentBinding
import com.fullstack.catawiki.extensions.extraKey
import com.fullstack.catawiki.models.CatItem
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class CatInfoFragment : BaseFragment<CatInfoViewModel, CatInfoFragmentBinding>() {

    lateinit var toolbar: Toolbar
    override val viewModel: CatInfoViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = CatInfoFragmentBinding.inflate(inflater, container, false)

    override fun initView(view: View, savedInstanceState: Bundle?) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        //val root = inflater.inflate(R.layout.cat_info_fragment, container, false)

        // Find the toolbar view inside the activity layout
        toolbar = root?.findViewById(R.id.toolbar) as Toolbar
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        viewModel.init(arguments)

        viewModel.catName.observe(viewLifecycleOwner, Observer<String> { name ->
            binding.tvCatName.text = name
            toolbar.title = name
        })

        viewModel.catDetails.observe(viewLifecycleOwner, Observer<String> { name ->
            binding.tvCatDescription.text = name
        })

        viewModel.catPicUrl.observe(viewLifecycleOwner, Observer<String> { name ->
            Glide.with(binding.ivCat).load(name).into(binding.ivCat)
        })

        return root
    }

    companion object {
        val ARGS by extraKey()
        fun getInstance(catId: String): Fragment {
            val retFragment = CatInfoFragment()
            val bundle = Bundle()
            bundle.putSerializable(ARGS, Arguments(catId))
            retFragment.arguments = bundle
            return retFragment
        }
    }

    class Arguments(val catId: String) : Serializable
}