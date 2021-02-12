package com.fullstack.catawiki.repositories

import com.fullstack.catawiki.models.CatImageResponse
import com.fullstack.catawiki.models.CatResponseItem

interface VisualsRepository {
    suspend fun getAllVisuals(): List<CatResponseItem>
    suspend fun getOneVisual(catId: String): CatImageResponse
}