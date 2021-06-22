package com.fullstack.catawiki.extensions

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun Boolean.toVisibility():Int {
    if (this) return View.VISIBLE else return View.GONE
}

fun extraKey() = object : ReadOnlyProperty<Any?, String> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "${thisRef?.javaClass?.canonicalName ?: ""}-${property.name}"
    }
}

@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(url: String?) {
    if (url != null && url.isNotBlank()) {
        Glide.with(this).load(url).into(this).clearOnDetach()
    }
}

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.adapter = adapter
    }
}
