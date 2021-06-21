package com.fullstack.catawiki.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.fullstack.catawiki.R
import com.fullstack.catawiki.adapters.PictureGridAdapter
import com.fullstack.catawiki.base.BaseFragment
import com.fullstack.catawiki.databinding.CatInfoFragmentBinding
import com.fullstack.catawiki.databinding.CatsGridFragmentBinding
import com.fullstack.catawiki.extensions.toVisibility
import com.fullstack.catawiki.models.CatItem
import com.fullstack.catawiki.views.MyGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Provider

@AndroidEntryPoint
class CatsGridFragment: BaseFragment<CatGridViewModel, CatsGridFragmentBinding>(), CatsGridView {

    private lateinit var pictureGridAdapter: PictureGridAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    override val viewModel: CatGridViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = CatsGridFragmentBinding.inflate(inflater, container, false)

    override fun initView(view: View, savedInstanceState: Bundle?) {
//        toolbar = binding.root.findViewById(R.id.toolbar) as Toolbar
//        toolbar.setNavigationOnClickListener {
//            activity?.onBackPressed()
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.cats_grid_fragment, container, false)
        recyclerView = root.findViewById(R.id.pictures_grid)
        progressBar = root.findViewById(R.id.progress_bar)
        viewManager = MyGridLayoutManager(requireContext(), 2)
        //presenter.loadImages(this)
        return root
    }

     override fun setData(pics: List<CatItem>) {
        this.apply {
            activity?.let{
                val data = pics.toMutableList()
                pictureGridAdapter = PictureGridAdapter(data,
                    object :PictureGridAdapter.ItemClickListener{
                        override fun onItemLongClick(position: Int) {
                            //presenter.notifyStatusChanged(position, data[position], !data[position].isSelected)
                            // pictureGridAdapter.notifyItemChanged(position)
                        }

                        override fun onItemClick(position: Int) {
                            TODO("Replace by viewmodel")
                            //presenter.onItemClick(data[position])
                        }

                        override fun onTagsCounterClick(position: Int) {
                            //presenter.onTagsCounterClick(data[position])
                        }
                    })
                recyclerView.apply {
                    // use this setting to improve performance if you know that changes
                    // in content do not change the layout size of the RecyclerView
                    setHasFixedSize(true)

                    // use a linear layout manager
                    layoutManager = viewManager

                    // specify an viewAdapter (see also next example)
                    adapter = pictureGridAdapter
                }
            }

        }
    }

    override fun setProgressBarVisibility(visible: Boolean) {
        progressBar.visibility = visible.toVisibility()
    }

    override fun startViewImage(catId: String) {
        activity?.let {
            it.supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, CatInfoFragment.getInstance(catId))
                .addToBackStack("cat_image")
                .commit()
        }
    }
}