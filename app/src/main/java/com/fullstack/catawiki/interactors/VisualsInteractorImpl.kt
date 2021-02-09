package com.fullstack.catawiki.interactors

import com.fullstack.catawiki.models.CatImageResponse
import com.fullstack.catawiki.models.CatItem
import com.fullstack.catawiki.models.CatResponseItem
import com.fullstack.catawiki.providers.LocalCache
import com.fullstack.catawiki.repositories.VisualsRepository
import io.reactivex.Observable

class VisualsInteractorImpl(private val repositoryVisuals: VisualsRepository, private val localCache: LocalCache) : VisualsInteractor {

    override fun getOneVisual(catId: String): Observable<CatItem> {
        return repositoryVisuals.getOneVisual(catId)
                .map { list -> dtoToData(list) }
        }

    override fun getAllVisuals(): Observable<List<CatItem>> {
        if (localCache.isNotEmpty()) return Observable.just(localCache.cachedCats)
        return repositoryVisuals.getAllVisuals()
            .map { list -> dtoToData(list) }
            .doOnNext { localCache.setCatItems(it) }
    }

    private fun dtoToData(catsList: List<CatResponseItem>): List<CatItem> {
        return catsList.map {
            CatItem(
                it.id,
                it.name,
                it.description,
                it.image.url)
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