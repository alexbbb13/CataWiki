package com.fullstack.catawiki.extensions

import android.view.View
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
