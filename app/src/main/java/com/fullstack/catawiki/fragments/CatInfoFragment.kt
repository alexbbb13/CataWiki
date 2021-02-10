package com.fullstack.catawiki.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fullstack.catawiki.R
import com.fullstack.catawiki.adapters.PictureGridAdapter
import com.fullstack.catawiki.base.BaseFragment
import com.fullstack.catawiki.extensions.extraKey
import com.fullstack.catawiki.extensions.toVisibility
import com.fullstack.catawiki.models.CatItem
import com.fullstack.catawiki.presenters.CatInfoPresenter
import com.fullstack.catawiki.views.MyGridLayoutManager
import dagger.android.support.AndroidSupportInjection
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Provider

class CatInfoFragment : BaseFragment(), CatInfoView {

    @Inject
    lateinit var presenterProvider: Provider<CatInfoPresenter>

    @InjectPresenter
    lateinit var presenter: CatInfoPresenter

    @ProvidePresenter
    fun providePresenter(): CatInfoPresenter {
        val presenter = presenterProvider.get()
        return presenter
    }

    lateinit var catView: ImageView
    lateinit var catTitle: TextView
    lateinit var catDescription: TextView
    lateinit var progressBar: ProgressBar

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_cat_info, container, false)
        catView = root.findViewById(R.id.iv_cat)
        progressBar = root.findViewById(R.id.progress_bar)
        catTitle = root.findViewById(R.id.tv_cat_name)
        catDescription = root.findViewById(R.id.tv_cat_description)
        presenter.loadImages(arguments?.getSerializable(ARGS) as Arguments)
        return root
    }

    override fun setCatPicUrl(url: String) {
        Glide.with(catView).load(url).into(catView)
    }

    override fun setCatName(name: String) {
        catTitle.text = name
    }

    override fun setCatInfo(info: String) {
        catDescription.text = info
    }

    override fun setProgressBarVisibility(visible: Boolean) {
        progressBar.visibility = visible.toVisibility()
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