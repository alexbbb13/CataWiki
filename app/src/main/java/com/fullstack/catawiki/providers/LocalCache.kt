package com.fullstack.catawiki.providers

import com.fullstack.catawiki.models.CatItem

class LocalCache {
    var cachedCats: List<CatItem>? = null
    fun setCatItems(updatedCache: List<CatItem>) {
        cachedCats = updatedCache
    }

    fun isNotEmpty(): Boolean {
        return !cachedCats.isNullOrEmpty()
    }
}