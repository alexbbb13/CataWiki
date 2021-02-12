package com.fullstack.catawiki.repositories

import com.fullstack.catawiki.models.CatImageResponse
import com.fullstack.catawiki.models.CatResponseItem
import com.fullstack.catawiki.providers.CatNetworkProvider


class VisualsRepositoryImpl(val catNetworkProvider: CatNetworkProvider):VisualsRepository {
    override suspend fun getAllVisuals(): List<CatResponseItem> {
        return catNetworkProvider.getAllVisuals()
    }

    override suspend fun getOneVisual(catId: String): CatImageResponse {
        return catNetworkProvider.getOneVisual(catId)
    }
}