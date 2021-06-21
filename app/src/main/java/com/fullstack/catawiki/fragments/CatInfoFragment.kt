package com.fullstack.catawiki.fragments

import android.os.Bundle
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
class CatInfoFragment : BaseFragment<CatInfoViewModel, CatInfoFragmentBinding>(), CatInfoView {

    lateinit var toolbar: Toolbar
    override val viewModel: CatInfoViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = CatInfoFragmentBinding.inflate(inflater, container, false)

    override fun initView(view: View, savedInstanceState: Bundle?) {
        toolbar = binding.root.findViewById(R.id.toolbar) as Toolbar
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated(view, arguments)
    }
      override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.cat_info_fragment, container, false)
        // Find the toolbar view inside the activity layout
        toolbar = root.findViewById(R.id.toolbar) as Toolbar
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
          viewModel.catResult.observe(this.viewLifecycleOwner, Observer<ResultWrapper<CatItem?>> { catResultWrapper ->
              when (catResultWrapper) {
                  is ResultWrapper.Success -> {
                      catResultWrapper.value?.let {
                          setCatPicUrl(it.pictureUrl!!)
                          setCatName(it.name)
                          setCatInfo(it.description)
                      }
                  }
                  is ResultWrapper.GenericError -> onServerError(catResultWrapper.throwable)
                  is ResultWrapper.NetworkError -> onNetworkError(root.findViewById(R.id.coordinatorLayout))

              }
          })
        return root
    }

    override fun setCatPicUrl(url: String) {
        Glide.with(binding.ivCat).load(url).into(binding.ivCat)
    }

    override fun setCatName(name: String) {
        binding.tvCatName.text = name
        toolbar.title = name
    }

    override fun setCatInfo(info: String) {
        binding.tvCatDescription.text = info
    }

    override fun setProgressBarVisibility(visible: Boolean) {
        //progressBar.visibility = visible.toVisibility()
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