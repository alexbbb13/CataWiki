package com.fullstack.catawiki.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.fullstack.catawiki.R
import com.fullstack.catawiki.adapters.CatsListAdapter
import com.fullstack.catawiki.adapters.PictureGridAdapter
import com.fullstack.catawiki.api.ResultWrapper
import com.fullstack.catawiki.base.BaseFragment
import com.fullstack.catawiki.databinding.CatInfoFragmentBinding
import com.fullstack.catawiki.databinding.CatsGridFragmentBinding
import com.fullstack.catawiki.extensions.toVisibility
import com.fullstack.catawiki.models.CatItem
import com.fullstack.catawiki.views.MyGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Provider

@AndroidEntryPoint
class CatsGridFragment: BaseFragment<CatGridViewModel, CatsGridFragmentBinding>() {

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var pictureGridAdapter: PictureGridAdapter
    private lateinit var recyclerView: RecyclerView

    override val viewModel: CatGridViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = CatsGridFragmentBinding.inflate(inflater, container, false)

    override fun initView(view: View, savedInstanceState: Bundle?) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.cats_grid_fragment, container, false)
        viewManager = MyGridLayoutManager(requireContext(), 2)
        viewModel.init(null)
        viewModel.catListResult.observe(viewLifecycleOwner, Observer<ResultWrapper<List<CatItem>>> { catListResultWrapper ->
            when (catListResultWrapper) {
                is ResultWrapper.Success -> {
                    catListResultWrapper.value?.let {
                        setData(it)
                    }
                }
                is ResultWrapper.GenericError -> {catListResultWrapper.throwable.printStackTrace()}//onServerError(catListResultWrapper.throwable)
                is ResultWrapper.NetworkError -> onNetworkError(root!!.findViewById(R.id.coordinatorLayout))
            }
        })
        recyclerView = root!!.findViewById(R.id.pictures_grid)
        return root
    }

     fun setData(pics: List<CatItem>) {
        this.apply {
              pictureGridAdapter = PictureGridAdapter(pics,
                    object :PictureGridAdapter.ItemClickListener{
                        override fun onItemLongClick(position: Int) {
                        }

                        override fun onItemClick(position: Int) {
                            viewModel.startViewImage(pics[position], activity?.supportFragmentManager)
                        }
                    })
                recyclerView.apply {
                    setHasFixedSize(true)
                    layoutManager = viewManager
                    adapter = pictureGridAdapter
                }
            }

        }
}


