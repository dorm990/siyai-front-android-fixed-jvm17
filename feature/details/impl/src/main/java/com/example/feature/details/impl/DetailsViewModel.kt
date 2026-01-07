package com.example.feature.details.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.analytics.AnalyticsTracker
import com.example.core.db.FavoriteEntity
import com.example.core.db.FavoritesDao
import com.example.core.network.ProductsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val api: ProductsApi,
    private val favoritesDao: FavoritesDao,
    private val analytics: AnalyticsTracker
) : ViewModel() {

    var title: String = ""
        private set
    var desc: String = ""
        private set
    var thumb: String = ""
        private set
    var isFav: Boolean = false
        private set

    fun load(id: Int, onUpdate: () -> Unit) {
        analytics.trackScreen("Details_${'$'}id")
        viewModelScope.launch {
            val p = api.getProduct(id)
            title = p.title
            desc = p.description
            thumb = p.thumbnail
            isFav = favoritesDao.isFavorite(id)
            onUpdate()
        }
    }

    fun toggle(id: Int, onUpdate: () -> Unit) {
        viewModelScope.launch {
            if (favoritesDao.isFavorite(id)) favoritesDao.remove(id) else favoritesDao.add(FavoriteEntity(id))
            isFav = favoritesDao.isFavorite(id)
            onUpdate()
        }
    }
}
