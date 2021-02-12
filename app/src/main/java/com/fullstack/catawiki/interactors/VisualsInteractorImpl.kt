package com.fullstack.catawiki.interactors

import com.fullstack.catawiki.api.ResultWrapper
import com.fullstack.catawiki.api.safeApiCall
import com.fullstack.catawiki.models.CatImageResponse
import com.fullstack.catawiki.models.CatItem
import com.fullstack.catawiki.models.CatResponseItem
import com.fullstack.catawiki.providers.LocalCache
import com.fullstack.catawiki.repositories.VisualsRepository
import kotlinx.coroutines.Dispatchers

class VisualsInteractorImpl(private val repositoryVisuals: VisualsRepository, private val localCache: LocalCache) : VisualsInteractor {

    override suspend fun getOneVisual(catId: String): ResultWrapper<CatItem> {
        return safeApiCall(Dispatchers.IO, { dtoToData(repositoryVisuals.getOneVisual(catId))} )
        }

    override suspend fun getAllVisuals(): ResultWrapper<List<CatItem>> {
        localCache.cachedCats?.let {return ResultWrapper.Success<List<CatItem>>(it)}
        val dtoToData: List<CatItem> = dtoToData(repositoryVisuals.getAllVisuals())
        localCache.setCatItems(dtoToData)
        return safeApiCall(Dispatchers.IO, {
            dtoToData
        })
    }

    private fun dtoToData(catsList: List<CatResponseItem>): List<CatItem> {
        return catsList.map {
            CatItem(
                it?.referenceImageId?:it.image?.id?:"NoImageIdAtAll",
                it.name,
                it.description,
                it.image?.url)
        }
    }

    private fun dtoToData(catImageResponse: CatImageResponse): CatItem {
        return CatItem(
                catImageResponse.id,
                catImageResponse.breeds[0].name,
                catImageResponse.breeds[0].description,
                catImageResponse.url)
    }
}