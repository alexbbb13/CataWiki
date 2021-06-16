package com.fullstack.catawiki.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.fullstack.catawiki.R
import com.fullstack.catawiki.adapters.PictureGridAdapter
import com.fullstack.catawiki.base.BaseFragment
import com.fullstack.catawiki.extensions.toVisibility
import com.fullstack.catawiki.models.CatItem
import com.fullstack.catawiki.presenters.CatsGridPresenter
import com.fullstack.catawiki.views.MyGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class CatsGridFragment: BaseFragment(), CatsGridView {

    @Inject
    lateinit var presenterProvider: Provider<CatsGridPresenter>

    @InjectPresenter
    lateinit var presenter: CatsGridPresenter

    @ProvidePresenter
    fun providePresenter(): CatsGridPresenter {
        val presenter = presenterProvider.get()
        return presenter
    }

    private lateinit var pictureGridAdapter: PictureGridAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_picture_grid, container, false)
        recyclerView = root.findViewById(R.id.pictures_grid)
        progressBar = root.findViewById(R.id.progress_bar)
        viewManager = MyGridLayoutManager(context!!, 2)
        presenter.loadImages(this)
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
                            presenter.onItemClick(data[position])
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